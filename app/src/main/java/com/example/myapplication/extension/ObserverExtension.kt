package com.example.myapplication.extension

import androidx.lifecycle.Observer

class NonNullObserver<T>(private val action: (T) -> Unit) : Observer<T> {
    override fun onChanged(t: T?) {
        if (t != null) {
            action(t)
        }
    }
}