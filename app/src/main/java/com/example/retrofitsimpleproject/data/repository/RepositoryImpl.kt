package com.example.retrofitsimpleproject.data.repository

import com.example.retrofitsimpleproject.data.api.ApiService
import com.example.retrofitsimpleproject.data.models.ResponseCats
import com.example.retrofitsimpleproject.domain.repository.Repository
import retrofit2.Response

class RepositoryImpl() : Repository {

    override suspend fun catInfo(jsonBool: Boolean): Response<ResponseCats> {

        return ApiService.ApiServiceImpl().catInfo(jsonBool)
    }

}