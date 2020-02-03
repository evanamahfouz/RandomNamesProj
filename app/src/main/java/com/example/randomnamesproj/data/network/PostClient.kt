package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.RandomName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostClient {

    companion object {
        var randomInterface: RandomNameAPI? = null
        const val BaseUrl = "https://uinames.com/"
        val Instant by lazy {
            PostClient()
        }
    }

    init {

        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
        randomInterface = retrofit.create(RandomNameAPI::class.java)
    }

    internal fun getCallRandomName(gender: String): Call<List<RandomName>>? {
        return randomInterface?.getRandomName(gender)
    }


}