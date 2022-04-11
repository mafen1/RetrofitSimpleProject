package com.example.retrofitsimpleproject.data.api

import com.example.retrofitsimpleproject.data.models.ResponseCats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/cat")
    suspend fun catInfo(@Query("json") json: Boolean): Response<ResponseCats>
    // инициализируем метод в интерфейсе
    class ApiServiceImpl: ApiService{
        override suspend fun catInfo(json: Boolean): Response<ResponseCats> {
            return ApiClient.getApiService().catInfo(json)
        }
    }
}