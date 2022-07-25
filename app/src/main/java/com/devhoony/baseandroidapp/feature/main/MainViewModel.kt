package com.devhoony.baseandroidapp.feature.main

import androidx.lifecycle.*
import com.devhoony.baseandroidapp.mapper.GithubViewMapper
import com.devhoony.baseandroidapp.util.DLog
import com.devhoony.baseandroidapp.util.base.BaseViewModel
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.usecase.github.GetGithubReposUseCase
import com.devhoony.domain.usecase.github.GetGithubUserFlowUseCase
import com.devhoony.domain.usecase.github.GetGithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapper: GithubViewMapper,
    private val getGithubUserUseCase: GetGithubUserUseCase,
    private val getGithubReposUseCase: GetGithubReposUseCase,
    private val getGithubUserFlowUseCase: GetGithubUserFlowUseCase,
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


    //
//    val githubZipLiveData: LiveData<ArrayList<GithubInfoView>> get() = _githubZipFlow.asLiveData()
//    var githubZipLiveData: LiveData<ArrayList<GithubInfoView>>
//    private val _githubZipLiveData: MutableLiveData<List<GithubInfoView>> = MutableLiveData()
//    val githubZipLiveData: LiveData<List<GithubInfoView>> = _githubZipLiveData
    private var _githubZipFlow: Flow<List<GithubInfoView>> = flow<List<GithubInfoView>> { }

    //    val githubZipDataFlow: LiveData<List<GithubInfoView>> get() = _githubZipFlow.asLiveData()
    private val _githubZipLiveData: MutableLiveData<List<GithubInfoView>> = MutableLiveData()
    var githubZipDataFlow: LiveData<List<GithubInfoView>> = _githubZipLiveData

    suspend fun loadDataFlow(userName: String) {
//        viewModelScope.launch {
//            delay(2000)
//            DLog.e("launch in viewmodel scope")
//            getGithubUserFlowUseCase.run(userName)
//                .catch {
//                    DLog.e("catch : $this")
//                }.collect {
//                    DLog.e("collect : $it")
//                }
//        }


        // parallel test
        viewModelScope.launch {
            getGithubUserFlowUseCase.runInfos(userName).collect {
                DLog.e("flow event collect : $it")
                _githubZipLiveData.postValue(it.map {
                    if (it.user != null) {
                        GithubInfoView(userView = mapper.transform(it.user!!))
                    } else {
                        GithubInfoView(repoView = mapper.transform(it.repo!!))
                    }
                })
            }
        }

//        val userFlow = getGithubUserFlowUseCase.run(userName)
//        val repoFlow = getGithubUserFlowUseCase.runRepo(userName)
//
//        DLog.e("userFlow : $userFlow")
//        DLog.e("repoFlow : $repoFlow")
//
//        _githubZipFlow = userFlow.zip(repoFlow) { user, repos ->
//            val list = ArrayList<GithubInfoView>()
//
//            val userView =
//                GithubUserView(id = user.id, name = user.name, profileUrl = user.imageUrl)
//            list.add(GithubInfoView(userView = userView))
//
//            repos.map { model ->
//                val repoView = GithubRepoView(
//                    id = model.id,
//                    title = model.title,
//                    description = model.description,
//                    startCount = model.starCount
//                )
//                list.add(GithubInfoView(repoView = repoView))
//            }
//            DLog.e("list : $list")
//            list
//        }.flowOn(Dispatchers.IO)
//
//        githubZipDataFlow = _githubZipFlow.asLiveData()


//    _githubZipFlow = flow
//    {
//
//    }
//        viewModelScope.launch {
//            flow {
//
//            }
//
//        }

//        val githubFlow = flow<Unit> {
//            emit(loadUserData("dev-hoony"))
//            emit(loadGithubRepos("dev-hoony"))
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            githubFlow.flatMapMerge {
//                flow {
//                    emit(this)
//                }
//            }.collect {
//
//            }
//        }

    }


}