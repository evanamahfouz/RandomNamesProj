package com.example.randomnamesproj.ui.main.ui.female

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.data.repos.Repo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomNameViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val mutableList = MutableLiveData<List<RandomName>>()
    val mutableError = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()
    fun getPost(gender: String) {


        val list = repo.getNameList(gender)
        if (list is List<*>) {
            mutableList.value = list as List<RandomName>
        } else {
            list as Single<List<RandomName>>

            compositeDisposable.add(
                list.subscribeOn(Schedulers.io())
                    .observeOn(
                        AndroidSchedulers.mainThread()
                    )
                    .subscribe({ result ->

                        insertData(result)
                        mutableList.value = result
                    },
                        { o ->
                            mutableError.value = o.message

                        })
            )
        }
    }

    private fun insertData(result: List<RandomName>?) {
        repo.insertData(result!!)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}





