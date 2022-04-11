package com.example.retrofitsimpleproject.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.retrofitsimpleproject.R
import com.example.retrofitsimpleproject.core.ApiState
import com.example.retrofitsimpleproject.data.models.ResponseCats
import com.example.retrofitsimpleproject.data.repository.RepositoryImpl
import com.example.retrofitsimpleproject.databinding.ActivityMainBinding
import com.example.retrofitsimpleproject.domain.useCases.UseCase
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val repository = RepositoryImpl()
        private val viewModel : ViewModel by viewModels{
        ViewModelFabric(UseCase(repository))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initObservers()
    }
    private fun initData() {
        swipeRefresh()
    }

    private fun initObservers() {
        viewModel.catInfo.observe(this){
            when(it){
                ApiState.ApiEmpty -> {
                    showProgressBar()
                    hideContent()
                }
                is ApiState.Error -> {
                    showSnackBar(it.message)
                    hideProgressBar()
                    showContent()
                }
                ApiState.Loading ->  {
                    showProgressBar()
                    hideContent()
                }
                // * спросить про него у макса
                is ApiState.Success<*> -> {
                    hideProgressBar()
                    showContent()
                    updateUi(it.data as ResponseCats)
                }
            }
        }
    }

    private fun updateUi(catInfo: ResponseCats?) {
        Glide.with(this)
            .load("https://cataas.com/${catInfo?.url}")
            .into(binding.imageCat)

        binding.textCatId.text = "Id car: ${catInfo?.id ?: "нет id"}"
        binding.textDate.text = "Id car: ${catInfo?.createdAt ?: "нет даты"}"
    }
    private fun showContent() {
        binding.content.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(this, findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG ).show()
    }

    private fun hideContent() {
        binding.content.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun swipeRefresh() {
        binding.swipeRefreshContainer.setOnRefreshListener {
            viewModel.apply {
                viewModel.catInfo(true)
                binding.swipeRefreshContainer.isRefreshing = false
            }
        }
    }


}