package com.example.myapplication.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.CustomViewModelFactory

inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null) {
        ViewModelProviders.of(this)[T::class.java]
    } else {
        ViewModelProviders.of(this, CustomViewModelFactory(creator))[T::class.java]
    }
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null) {
        ViewModelProviders.of(this)[T::class.java]
    } else {
        ViewModelProviders.of(this, CustomViewModelFactory(creator))[T::class.java]
    }
}