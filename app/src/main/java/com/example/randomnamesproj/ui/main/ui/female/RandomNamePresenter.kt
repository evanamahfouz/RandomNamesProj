package com.example.randomnamesproj.ui.main.ui.female

import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.DataCallback
import com.example.randomnamesproj.data.repos.Repo

class RandomNamePresenter(var randomName: RandomNameView) {

    fun getPost(gender: String) {
        val repo = Repo(gender)
        repo.getNameList(object : DataCallback<List<RandomName>> {
            override fun onError(t: Throwable) {
            }

            override fun onSuccess(data: List<RandomName>) {
                getPostResponse(data)
            }


        })
    }

    fun getPostResponse(data: List<RandomName>) {
        randomName.onGetPost(data)
    }


}