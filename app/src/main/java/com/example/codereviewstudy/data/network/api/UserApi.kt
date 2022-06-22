package com.example.codereviewstudy.data.network.api

import com.example.codereviewstudy.data.entity.UserEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("{username}")
    fun getUserInfo(@Path("username") username: String): Single<UserEntity>
}