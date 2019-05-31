package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.myapplication.data.UserInfo
import com.example.myapplication.repository.PagingRepository

class FragmentViewModel(private val repository: PagingRepository) : BaseViewModel() {
    val userInput = MutableLiveData<String>()

    private val _pagedInfo = MutableLiveData<PagedList<UserInfo>>()
    val pagedInfo: LiveData<PagedList<UserInfo>> get() = _pagedInfo

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    fun onSearch() {
        userInput.value?.run {
            addSubscriptions(repository.getData(this)
                .doOnSubscribe { _progressBarVisibility.postValue(true) }
                .doOnError { _progressBarVisibility.postValue(false) }
                .doOnNext { _progressBarVisibility.postValue(false) }
                .subscribe { _pagedInfo.postValue(it) })
        }
    }
}