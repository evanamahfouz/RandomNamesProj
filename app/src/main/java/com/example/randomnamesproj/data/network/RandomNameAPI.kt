package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.RandomName
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomNameAPI {
    @GET("api/?amount=25")
    suspend fun getRandomName(@Query("gender") gender: String): List<RandomName>
}