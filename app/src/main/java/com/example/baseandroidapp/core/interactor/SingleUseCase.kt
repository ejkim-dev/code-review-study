package com.example.baseandroidapp.core.interactor

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

abstract class SingleUseCase<R, T> : UseCase() {

//    internal abstract fun buildUseCaseSingle(): Single<Response<T>>
    internal abstract fun buildUseCaseSingle(): Single<T>

    private var request: R? = null
    fun getRequestParams(): R? = request

    fun setRequestParams(request: R?) {
        this.request = request
    }

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {

        disposeLast()
        lastDisposable = buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.map { t -> if (t.isSuccessful) t else throw HttpException(t) }
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }

}