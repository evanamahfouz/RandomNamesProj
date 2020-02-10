package com.example.randomnamesproj

import android.app.Application

class App : Application() {


    companion object {
        private lateinit var instance: App

        fun application() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }

}