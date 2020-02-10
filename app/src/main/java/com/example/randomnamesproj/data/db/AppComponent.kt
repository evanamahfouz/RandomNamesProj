package com.example.randomnamesproj.data.db

import com.example.randomnamesproj.ui.main.ui.female.RandomNameViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun poke(viewModel: RandomNameViewModel)

}