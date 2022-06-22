package com.example.codereviewstudy.presentation.ui

import com.example.codereviewstudy.domain.usecase.GetUserUseCase
import com.example.codereviewstudy.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class InputUserGitNameViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    BaseViewModel() {

    fun getUserInfo(username: String) {
        disposable +=
        getUserUseCase.getUserInfo(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                Timber.d("getInfo : $data")

            }
    }
}