@file:Suppress("DEPRECATION")

package com.example.randomnamesproj.data.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.network.RandomNameAPI

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class Repo @Inject constructor(
    private val dB: RandomNameDataBase,
    private val randomNameApi: RandomNameAPI
) {


    fun getNameList(
        gender1: String,
        callback: DataCallback<List<RandomName>>
    ) {


        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {
            randomNameApi.getRandomName(gender = gender1)
                .enqueue(object : Callback<List<RandomName>> {
                    override fun onResponse(
                        call: Call<List<RandomName>>,
                        response: Response<List<RandomName>>
                    ) {

                        val newData: List<RandomName> = getNameList(response).orEmpty()
                        insertData(newData)

                        callback.onSuccess(newData)


                    }

                    override fun onFailure(call: Call<List<RandomName>>, t: Throwable) {
                        callback.onError(t)
                    }
                })
        } else {
            try {
                val items = dB.randomNameDOA().getAll(gender1)

                callback.onSuccess(items.map {
                    it.mapToExample(gender1)
                })
            } catch (ex: Exception) {
                callback.onError(ex)
            }
        }
    }

    fun insertData(response: List<RandomName>) {

        dB.randomNameDOA().insertAll(response.map {
            it.mapToRandomName()
        })

    }

    private fun getNameList(response: Response<List<RandomName>>): List<RandomName>? {
        return response.body()
    }


}
