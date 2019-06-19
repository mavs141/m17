package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ActivityViewModel : BaseViewModel() {
    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    fun setProgressbarVisibility(visible: Boolean) = _progressBarVisibility.postValue(visible)
}