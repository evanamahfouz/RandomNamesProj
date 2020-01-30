@file:Suppress("DEPRECATION")

package com.example.randomnamesproj.data.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.model.Example
import com.example.randomnamesproj.data.network.PostClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


@Suppress("DEPRECATION")
class RepoFemale {
    private val dB = RandomNameDataBase.getInstance()

    fun getNameFemaleList(callback: DataCallback<List<Example>>) {
        //val isConnected = true

        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        Log.v(
            "InsideFemaleDataBase", isConnected.toString()
        )
//        val Connect = true
        if (isConnected) {
            PostClient.Instant.getCallRandomName("female")
                ?.enqueue(object : Callback<List<Example>> {
                    override fun onResponse(
                        call: Call<List<Example>>,
                        response: Response<List<Example>>
                    ) {

                        val newData: List<Example> = getNameFemaleList(response)!!
                        Log.v("helloYEsResp", "Yes Internet Connection")
                        insertData(newData)

                        callback.onSuccess(newData)


                    }

                    override fun onFailure(call: Call<List<Example>>, t: Throwable) {
                        Log.v("helloNoResp", "No Internet Connection")
                        callback.onError(t)
                    }
                })
        } else {
            try {
                val items = dB.femaleNameDOA().getAll()
                Log.v("InsideFemaleDataBase", items.map {
                    it.mapToExample()
                }.size.toString())
                callback.onSuccess(items.map {
                    it.mapToExample()
                })
            } catch (ex: Exception) {
                callback.onError(ex)
            }
        }
    }

    fun insertData(response: List<Example>) {
        dB.femaleNameDOA().insertAll(response.map {
            it.mapToFemaleName()
        })

    }

    private fun getNameFemaleList(response: Response<List<Example>>): List<Example>? {
        return response.body()
    }


}
