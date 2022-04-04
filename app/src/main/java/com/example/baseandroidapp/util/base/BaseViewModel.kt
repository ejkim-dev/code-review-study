package com.example.baseandroidapp.util.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseandroidapp.core.exception.Failure
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel() : ViewModel() {


    // Coroutine + Functional
    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }


    //
    protected val _fetchState = MutableLiveData<FetchState>()
    val fetchState : LiveData<FetchState>
        get() = _fetchState

    protected val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()

        when(throwable){
            is SocketException -> _fetchState.postValue(FetchState.BAD_INTERNET)
            is HttpException -> _fetchState.postValue(FetchState.PARSE_ERROR)
            is UnknownHostException -> _fetchState.postValue(FetchState.WRONG_CONNECTION)
            else -> _fetchState.postValue(FetchState.FAIL)
        }
    }

}

enum class FetchState {
    BAD_INTERNET,
    PARSE_ERROR,
    WRONG_CONNECTION,
    FAIL
}