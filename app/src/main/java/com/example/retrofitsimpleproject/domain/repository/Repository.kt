package com.example.retrofitsimpleproject.domain.repository

import com.example.retrofitsimpleproject.data.models.ResponseCats
import retrofit2.Response

interface Repository {

    suspend fun catInfo(jsonBool: Boolean): Response<ResponseCats>

}