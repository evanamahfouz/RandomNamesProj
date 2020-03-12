package com.example.randomnamesproj.dagger.module

import androidx.room.Room
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.data.network.RandomNameAPI
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRandomDataBase(): RandomNameDataBase {
        return Room.databaseBuilder(
            App.application(),
            RandomNameDataBase::class.java, "random_name.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(): RandomNameAPI {


        val retrofit = Retrofit.Builder().baseUrl("https://uinames.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).addCallAdapterFactory(RxJava2CallAdapterFactory.create())


            .build()
        return retrofit.create(RandomNameAPI::class.java)
    }

}