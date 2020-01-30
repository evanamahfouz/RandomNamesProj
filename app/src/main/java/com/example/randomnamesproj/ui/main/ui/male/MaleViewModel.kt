package com.example.randomnamesproj.ui.main.ui.male

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.data.model.Example
import com.example.randomnamesproj.data.repos.DataCallback
import com.example.randomnamesproj.data.repos.RepoMale

class MaleViewModel : ViewModel() {
    val mutableList = MutableLiveData<List<Example>>()
    val mutableError = MutableLiveData<String>()

    init {
        getPost()
    }

    private fun getPost() {
        val repo = RepoMale()
        repo.getNameMaleList(object : DataCallback<List<Example>> {
            override fun onError(t: Throwable) {
                mutableError.value = t.message
            }

            override fun onSuccess(data: List<Example>) {
                mutableList.value = data
                mutableError.value = ""
            }


        })


    }
}
