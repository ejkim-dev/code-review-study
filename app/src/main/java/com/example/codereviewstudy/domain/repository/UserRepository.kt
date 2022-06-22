package com.example.codereviewstudy.domain.repository

import com.example.codereviewstudy.domain.model.User
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
interface UserRepository{
    fun getUser(username:String) : Single<User>

}