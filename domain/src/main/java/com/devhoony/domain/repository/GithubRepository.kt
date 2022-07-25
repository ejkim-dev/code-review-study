package com.devhoony.domain.repository

import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.entity.Vod
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
import kotlinx.coroutines.flow.Flow


interface GithubRepository {

    suspend fun getUser(userName: String): Either<Failure, GithubUser>
    suspend fun getRepos(userName: String): Either<Failure, List<GithubRepo>>

    suspend fun getUserFlow(userName: String): Flow<GithubUser>
    suspend fun getReposFlow(userName: String): Flow<List<GithubRepo>>

}