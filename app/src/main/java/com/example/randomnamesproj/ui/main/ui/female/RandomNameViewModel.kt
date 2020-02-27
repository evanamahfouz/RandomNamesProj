package com.example.randomnamesproj.ui.main.ui.female

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RandomNameViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<List<RandomName>>()
    val mutableError = MutableLiveData<String>()

    fun getPost(gender: String) {
        viewModelScope.launch {
            try {
                val list = withContext(Dispatchers.IO) {
                    repo.getNameList(gender)
                }
                mutableList.value = list
            } catch (e: Exception) {
                mutableError.value = e.message
            }
        }
    }
}
