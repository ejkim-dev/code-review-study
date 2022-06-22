package com.example.codereviewstudy.domain.usecase

import com.example.codereviewstudy.domain.model.User
import com.example.codereviewstudy.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun getUserInfo(username: String): Single<User> = userRepository.getUser(username)


}