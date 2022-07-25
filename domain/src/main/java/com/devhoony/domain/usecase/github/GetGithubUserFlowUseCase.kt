package com.devhoony.domain.usecase.github

import android.util.Log
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.usecase.interactor.UseCase
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGithubUserFlowUseCase
@Inject constructor(
    private val repository: GithubRepository
) {

    suspend fun run(userName: String): Flow<GithubUser> {
        val flow = repository.getUserFlow(userName)
        Log.e("usecase", "run ${flow}")

        return flow
    }

    suspend fun runRepo(userName: String): Flow<List<GithubRepo>> {
        return repository.getReposFlow(userName)
    }

    suspend fun runInfos(userName: String): Flow<List<GithubInfo>> {
        val userFlow = run(userName)
        val repoFlow = runRepo(userName)

        return userFlow.zip(repoFlow) { user, repos ->
            val list = ArrayList<GithubInfo>()

            list.add(GithubInfo(user = user))
            repos.map {
                list.add(GithubInfo(repo = it))
            }
//            list.add(GithubInfo(repos = repos))
            return@zip list
        }.catch {
            Log.e("usecase", "error")
        }
    }

}

data class GithubInfo(
    val user: GithubUser? = null,
//    val repos: List<GithubRepo>? = null,
    val repo: GithubRepo? = null,
)



