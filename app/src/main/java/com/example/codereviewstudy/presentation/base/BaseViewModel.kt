package com.example.codereviewstudy.presentation.base

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val disposable: CompositeDisposable = CompositeDisposable()
}