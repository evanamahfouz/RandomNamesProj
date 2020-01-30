package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomNameAPI {
    @GET("api/?amount=25")
    fun getRandomName(@Query("gender") gender: String): Call<List<Example>>
}