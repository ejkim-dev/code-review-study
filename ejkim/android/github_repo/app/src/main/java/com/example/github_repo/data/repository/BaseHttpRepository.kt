package com.example.github_repo.data.repository

import com.example.github_repo.data.entity.HttpResponseEntity
import com.google.gson.Gson
import com.google.gson.JsonElement

interface BaseHttpRepository<Type> {
    fun makeDataOrErrorResult(httpResponseJsonElement: JsonElement){
    }

    private fun checkError(httpResponseJsonElement: JsonElement){

    }

    private fun convertData(data: JsonElement, dataType: Class<Type>): Type {
        return Gson().fromJson(data, dataType)
    }
}