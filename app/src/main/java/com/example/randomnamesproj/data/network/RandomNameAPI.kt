package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.Example
import retrofit2.Call
import retrofit2.http.GET

interface RandomNameAPI {
    @GET("api/?amount=25&&gender=female")
    fun getRandomName(): Call<List<Example>>
}