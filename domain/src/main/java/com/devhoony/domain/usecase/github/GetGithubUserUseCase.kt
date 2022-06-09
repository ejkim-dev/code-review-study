package com.devhoony.domain.usecase.github

import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.usecase.interactor.UseCase
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGithubUserUseCase
@Inject constructor(
    private val repository: GithubRepository
) : UseCase<GithubUser, String>() {

    override suspend fun run(params: String): Either<Failure, GithubUser> {
        return repository.getUser(params)
    }

}


