package com.example.randomnamesproj.ui.main.ui.female

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.Repo
import java.lang.Exception
import javax.inject.Inject

class RandomNameViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<List<RandomName>>()
    val mutableError = MutableLiveData<String>()

    fun getPost(gender: String) {

        try {
            val list = repo.getNameList(gender)
            mutableList.value = list

        } catch (e: Exception) {
            mutableError.value = e.message
        }
    }
}

