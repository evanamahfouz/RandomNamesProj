@file:Suppress("DEPRECATION")

package com.example.randomnamesproj.data.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.network.RandomNameAPI

import javax.inject.Inject

class Repo @Inject constructor(
    private val dB: RandomNameDataBase,
    private val randomNameApi: RandomNameAPI
) {

    fun getNameList(gender1: String): Any {
        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return if (isConnected) {
            Log.v("helllo", "1")

            randomNameApi.getRandomName(gender = gender1)

        } else {
            dB.randomNameDOA().getAll(gender1).map {
                it.mapToExample(gender1)
            }
        }
    }

    fun insertData(response: List<RandomName>) {
        dB.randomNameDOA().insertAll(response.map {
            it.mapToRandomName()
        })

    }


}
