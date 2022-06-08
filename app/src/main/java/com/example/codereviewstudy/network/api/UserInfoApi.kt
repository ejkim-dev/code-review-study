package com.example.codereviewstudy.network.api

import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoApi {
    @GET("{username}")
    fun getUserInfo(@Path("username") username: String)
}