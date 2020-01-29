package com.example.randomnamesproj.ui.main.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.data.model.VolumeInfo
import com.example.randomnamesproj.data.repos.DataCallback
import com.example.randomnamesproj.data.repos.Repo

class PostViewModel : ViewModel() {

    val mutableList = MutableLiveData<List<VolumeInfo>>()
    val mutableError = MutableLiveData<String>()

    init {
        getPost()
    }

    private fun getPost() {
        val repo = Repo()
        repo.getVolumeList(object : DataCallback<List<VolumeInfo>> {
            override fun onError(t: Throwable) {
                mutableError.value = t.message
            }

            override fun onSuccess(data: List<VolumeInfo>) {
                mutableList.value = data
                mutableError.value = ""
            }


        })


    }


}