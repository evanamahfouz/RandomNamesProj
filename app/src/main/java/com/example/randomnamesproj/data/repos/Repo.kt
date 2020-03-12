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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class Repo @Inject constructor(
    private val dB: RandomNameDataBase,
    private val randomNameApi: RandomNameAPI
) {

    private lateinit var list: List<RandomName>
    fun getNameList(gender1: String): List<RandomName> {
        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return if (isConnected) {
            Log.v("helllo", "1")

            randomNameApi.getRandomName(gender = gender1)
                .subscribeOn(Schedulers.io())

                .observeOn(
                    AndroidSchedulers.mainThread()
                )
                .subscribe({ result ->

                    insertData(result)
                    list = result

                },
                    { o -> Log.v("helllo", o.message!! + o.toString()) }).dispose()
            list

        } else {
            dB.randomNameDOA().getAll(gender1).map {
                it.mapToExample(gender1)
            }
        }
    }

    private fun insertData(response: List<RandomName>) {
        dB.randomNameDOA().insertAll(response.map {
            it.mapToRandomName()
        })

    }


}
