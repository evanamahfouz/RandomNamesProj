package com.example.randomnamesproj.ui.main.ui.female

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.dagger.component.DaggerAppComponent
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.DataCallback
import com.example.randomnamesproj.data.repos.Repo
import javax.inject.Inject

class RandomNameViewModel : ViewModel() {
    val mutableList = MutableLiveData<List<RandomName>>()
    val mutableError = MutableLiveData<String>()
    @Inject
    lateinit var repo: Repo

    init {
        DaggerAppComponent.create().poke(this)

    }

    fun getPost(gender: String) {

        repo.getNameList(gender, object : DataCallback<List<RandomName>> {
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
