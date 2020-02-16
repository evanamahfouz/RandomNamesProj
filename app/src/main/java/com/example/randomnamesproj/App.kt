package com.example.randomnamesproj

import android.app.Application
import com.example.randomnamesproj.dagger.component.AppComponent
import com.example.randomnamesproj.dagger.component.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    companion object {
        private lateinit var instance: App

        fun application() = instance

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent.create()

    }

}