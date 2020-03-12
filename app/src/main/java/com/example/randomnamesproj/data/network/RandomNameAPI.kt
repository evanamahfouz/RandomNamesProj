package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.RandomName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomNameAPI {
    @GET("api/?amount=25")
    fun getRandomName(@Query("gender") gender: String): Single<List<RandomName>>
}