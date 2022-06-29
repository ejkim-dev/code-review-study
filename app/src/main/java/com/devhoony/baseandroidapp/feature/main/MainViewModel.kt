package com.devhoony.baseandroidapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devhoony.baseandroidapp.util.base.BaseViewModel
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.usecase.github.GetGithubReposUseCase
import com.devhoony.domain.usecase.github.GetGithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubUserUseCase: GetGithubUserUseCase,
    private val getGithubReposUseCase: GetGithubReposUseCase
) : BaseViewModel() {

    private val _userData: MutableLiveData<GithubUserView> = MutableLiveData()
    val userData: LiveData<GithubUserView> = _userData

    fun loadUserData(userName: String) {
//        _isLoading.value = true
//        DLog.e("vod")

        getGithubUserUseCase(userName, viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleUserData
            )
//            _isLoading.value = false
        }

    }

    private fun handleUserData(model: GithubUser) {
        _userData.value =
            GithubUserView(id = model.id, name = model.name, profileUrl = model.imageUrl)
    }


    private val _reposData: MutableLiveData<List<GithubRepoView>> = MutableLiveData()
    val reposData: LiveData<List<GithubRepoView>> = _reposData

    fun loadGithubRepos(userName: String) {
//        _isLoading.value = true
//        DLog.e("vod")

        getGithubReposUseCase(userName, viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleRepoData
            )
//            _isLoading.value = false
        }

    }

    private fun handleRepoData(list: List<GithubRepo>) {
        val sortList = list.sortedByDescending { it.starCount }
        _reposData.value = sortList.map { model ->
            GithubRepoView(
                id = model.id,
                title = model.title,
                description = model.description,
                startCount = model.starCount
            )
        }
    }


    val githubZipData = MediatorLiveData<ArrayList<GithubInfoView>>()

    init {
        // 1-1 MediatorLiveData
//        githubZipData.addSource(userData) {
//            val list = githubZipData.value ?: ArrayList<GithubInfoView>()
//            try {
//                list[0] = GithubInfoView(userView = it)
//            } catch (e: IndexOutOfBoundsException) {
//                list.add(GithubInfoView(userView = it))
//            }
//            githubZipData.value = list
//        }
//        githubZipData.addSource(reposData) { repoList ->
//            val list = githubZipData.value ?: ArrayList<GithubInfoView>()
//            repoList.map {
//                list.add(GithubInfoView(repoView = it))
//            }
//            githubZipData.value = list
//        }

        // 1-2 1-1에서 변경 이벤트가 2번 발생하는 문제 수정.
        // 메소드 내에서 2개의 데이터 변경을 모두 확인하고 최종 데이터를 변경.
        with(githubZipData) {
            addSource(userData) {
                checkGithubInfoData(userData, reposData)
            }
            addSource(reposData) {
                checkGithubInfoData(userData, reposData)
            }
        }
    }

    private fun checkGithubInfoData(
        userInfo: LiveData<GithubUserView>,
        repoInfo: LiveData<List<GithubRepoView>>
    ) {
        if (userInfo.value != null && repoInfo.value != null) {
            val list = ArrayList<GithubInfoView>()
            userInfo.value?.let {
                list.add(GithubInfoView(userView = it))
            }
            repoInfo.value?.let {
                it.map {
                    list.add(GithubInfoView(repoView = it))
                }
            }
            githubZipData.value = list
        }
    }


}