package com.example.randomnamesproj.dagger.component

import com.example.randomnamesproj.dagger.module.AppModule
import com.example.randomnamesproj.ui.main.ui.female.RandomNameViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun poke(viewModel: RandomNameViewModel)

}