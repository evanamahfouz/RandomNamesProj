package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.Example
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostClient {

    companion object {
        var randomIntefracre: RandomNameAPI? = null
        val BaseUrl = "https://uinames.com/"
        val Instant by lazy {
            PostClient()
        }
    }

    init {

        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
        randomIntefracre = retrofit.create(RandomNameAPI::class.java)
    }

    internal fun getCallRandomName(): Call<List<Example>>? {
        return randomIntefracre?.getRandomName()
    }


}