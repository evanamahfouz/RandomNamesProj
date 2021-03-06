package com.example.randomnamesproj

import android.app.Application
import com.example.randomnamesproj.dagger.component.AppComponent
import com.example.randomnamesproj.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {


    lateinit var component: AppComponent

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    companion object {
        private lateinit var instance: App

        fun application() = instance

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>? = fragmentInjector


}