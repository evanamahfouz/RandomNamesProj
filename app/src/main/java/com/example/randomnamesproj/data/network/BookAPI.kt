package com.example.randomnamesproj.data.network

import com.example.randomnamesproj.data.model.BookObject
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {
    @GET("books/v1/volumes?q=9781451648546")
    fun getBook(): Call<BookObject>
}