package com.example.retrofitsimpleproject.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitsimpleproject.domain.useCases.UseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFabric(private val catUseCase: UseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(com.example.retrofitsimpleproject.ui.screens.ViewModel::class.java)){
            com.example.retrofitsimpleproject.ui.screens.ViewModel(this.catUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}