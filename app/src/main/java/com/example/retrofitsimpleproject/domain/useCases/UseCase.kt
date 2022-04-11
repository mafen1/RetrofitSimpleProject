package com.example.retrofitsimpleproject.domain.useCases

import com.example.retrofitsimpleproject.domain.repository.Repository

class UseCase(private val repository: Repository) {

    suspend fun catInfo(json: Boolean) = repository.catInfo(json)

}