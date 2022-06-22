package com.example.codereviewstudy.data.repository

import com.example.codereviewstudy.data.mapper.UserEntityMapper
import com.example.codereviewstudy.data.network.api.UserApi
import com.example.codereviewstudy.domain.model.User
import com.example.codereviewstudy.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userEntityMapper: UserEntityMapper
) : UserRepository {
    override fun getUser(username: String): Single<User> {
        return userApi.getUserInfo(username).map(userEntityMapper::transform)
    }
}