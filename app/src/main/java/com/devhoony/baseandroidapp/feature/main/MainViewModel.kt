package com.devhoony.baseandroidapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devhoony.baseandroidapp.util.base.BaseViewModel
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.usecase.github.GetGithubReposUseCase
import com.devhoony.domain.usecase.github.GetGithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
        _reposData.value = list.map { model ->
            GithubRepoView(
                id = model.id,
                title = model.title,
                description = model.description,
                startCount = model.starCount
            )
        }
    }


}