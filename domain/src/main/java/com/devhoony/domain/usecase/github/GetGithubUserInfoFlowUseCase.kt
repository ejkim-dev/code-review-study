package com.devhoony.domain.usecase.github

import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.usecase.interactor.UseCase
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGithubUserInfoFlowUseCase
@Inject constructor(
    private val repository: GithubRepository
)  {

    suspend fun run(userName: String): Flow<GithubUser> {
        val userFlow = repository.getUserFlow(userName)
        val repoFlow = repository.getReposFlow(userName)


        return flow{
//            emit(userFlow)
//            emit(repoFlow)
//        }.flatMapConcat {
//            flow{
//
//            }
        }
//       return repository.getUserFlow(userName)
    }

}


