package com.example.retrofitsimpleproject.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitsimpleproject.core.ApiState
import com.example.retrofitsimpleproject.data.models.ResponseCats
import com.example.retrofitsimpleproject.domain.useCases.UseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModel(private val useCase: UseCase): ViewModel() {

    private var _catInfo = MutableLiveData<ApiState>(ApiState.ApiEmpty)
    val catInfo: LiveData<ApiState> = _catInfo

    init {
        catInfo(true)
    }

    fun catInfo(jsonBoolean: Boolean){
        _catInfo.value = ApiState.Loading
        viewModelScope.launch {
            delay(500)

            if (useCase.catInfo(jsonBoolean).isSuccessful){
                _catInfo.postValue(ApiState.Success(useCase.catInfo(jsonBoolean).body()))
            }else{
                _catInfo.value = ApiState.Error("some problems")
            }

        }
    }
}