@file:Suppress("DEPRECATION")

package com.example.randomnamesproj.data.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.network.RandomNameAPI

import javax.inject.Inject

class Repo @Inject constructor(
    private val dB: RandomNameDataBase,
    private val randomNameApi: RandomNameAPI
) {


    suspend fun getNameList(gender1: String): List<RandomName> {
        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return if (isConnected) {
            randomNameApi.getRandomName(gender = gender1).also {
                insertData(it)
            }
        } else {
            dB.randomNameDOA().getAll(gender1).map {
                it.mapToExample(gender1)
            }
        }
    }

    suspend fun insertData(response: List<RandomName>) {

        dB.randomNameDOA().insertAll(response.map {
            it.mapToRandomName()
        })

    }


}
