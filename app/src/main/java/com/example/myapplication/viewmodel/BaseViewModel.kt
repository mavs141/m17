package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    protected val subscriptions = CompositeDisposable()

    protected fun addSubscriptions(vararg subsriptions: Disposable) = this.subscriptions.addAll(*subsriptions)

    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }
}