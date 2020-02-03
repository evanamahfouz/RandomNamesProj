package com.example.randomnamesproj.ui.main.ui.female

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.DataCallback
import com.example.randomnamesproj.data.repos.Repo

class RandomNameViewModel : ViewModel() {
    val mutableList = MutableLiveData<List<RandomName>>()
    val mutableError = MutableLiveData<String>()

    fun getPost(gender: String) {
        val repo = Repo(gender)
        repo.getNameList(object : DataCallback<List<RandomName>> {
            override fun onError(t: Throwable) {
                mutableError.value = t.message
            }

            override fun onSuccess(data: List<RandomName>) {
                mutableList.value = data
                mutableError.value = ""
            }


        })


    }
}
