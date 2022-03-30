package com.example.baseandroidapp.core.interactor

abstract class CoUseCase<Params, Return> {
    abstract suspend fun run(params: Params): Return
}