@file:Suppress("DEPRECATION")

package com.example.randomnamesproj.data.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.App
import com.example.randomnamesproj.data.db.RandomNameEntity
import com.example.randomnamesproj.data.model.VolumeInfo
import com.example.randomnamesproj.data.model.BookObject
import com.example.randomnamesproj.data.network.PostClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


@Suppress("DEPRECATION")
class Repo {
    private val dB = RandomNameDataBase.getInstance()

    fun getVolumeList(callback: DataCallback<List<VolumeInfo>>) {
        //val isConnected = true

        val cm =
            App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        Log.v(
            "InsideVoulmDataBase", isConnected.toString()
        )
//        val Connect = true
        if (isConnected) {
            PostClient.Instant.getCallBookObject()?.enqueue(object : Callback<BookObject> {
                override fun onResponse(call: Call<BookObject>, response: Response<BookObject>) {

                    val newData: List<VolumeInfo> = changeToVolum(response)!!
                    Log.v("helloYEsResp", "Yes Internet Connection")
                    insertData(newData)

                    callback.onSuccess(newData)


                }

                override fun onFailure(call: Call<BookObject>, t: Throwable) {
                    Log.v("helloNoResp", "No Internet Connection")
                    callback.onError(t)
                }
            })
        } else {
            try {
                val items = dB.randomNameDOA().getAll()
                Log.v("InsideVoulmDataBase", items.map {
                    it.mapToVolumInfo()
                }.size.toString())
                callback.onSuccess(items.map {
                    it.mapToVolumInfo()
                })
            } catch (ex: Exception) {
                callback.onError(ex)
            }
        }
    }

    fun insertData(response: List<VolumeInfo>) {
        dB.randomNameDOA().insertAll(response.map {
            it.mapToVolumInfoEntity()
        })

    }

    private fun changeToVolum(response: Response<BookObject>): List<VolumeInfo>? {
        return response.body()?.items?.mapNotNull {
            it.volumeInfo
        }
    }

    fun showData(): List<RandomNameEntity> {
        return dB.randomNameDOA().getAll()
    }
}
