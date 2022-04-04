package com.example.baseandroidapp.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidapp.core.data.EmptyRequest
import com.example.baseandroidapp.core.domain.GetVodListCoUseCase
import com.example.baseandroidapp.core.domain.GetVodListRxUseCase
import com.example.baseandroidapp.core.domain.GetVodListUseCase
import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.core.interactor.UseCase
import com.example.baseandroidapp.core.interactor.UseCase.None
import com.example.baseandroidapp.util.DLog
import com.example.baseandroidapp.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VodViewModel
@Inject constructor(
    private val getVodListUseCase: GetVodListUseCase,
    private val getVodListRxUseCase: GetVodListRxUseCase,
    private val getVodListCoUseCase: GetVodListCoUseCase
) : BaseViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _vodList: MutableLiveData<List<VodView>> = MutableLiveData()
    val vodList: LiveData<List<VodView>> = _vodList

    fun loadVodList(){
        _isLoading.value = true
        DLog.e("vod")
        getVodListUseCase(None(),viewModelScope){
            it.fold(
             ::handleFailure,
             ::handleVodList
            )
        }
        _isLoading.value = false
    }

    fun loadVodListRx() {
        _isLoading.value = true
        DLog.e("vod")
        getVodListRxUseCase.execute(
            onSuccess = {
                DLog.e("loadVodList: $it")
                handleVodList(it)
            },
            onError = {
                DLog.e("error: $it")
            },
            onFinished = {
                _isLoading.value = false
                DLog.e("finish")
            }
        )
    }

    private fun handleVodList(vodList: List<Vod>) {
        _vodList.value =
            vodList.map { VodView(it.id, it.title, it.imageUrl) }
    }

// coroutine error handling - 1. use try ~ catch
//    fun loadVodListCo() {
//        _isLoading.value = true
//        DLog.e("vod")
//        viewModelScope.launch {
//            try {
//                val list = getVodListCoUseCase.run(EmptyRequest())
//                handleVodList(list)
//
//            } catch (e: Exception) {
//                DLog.e("network error")
//                e.printStackTrace()
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

    //
    fun loadVodListCo() = viewModelScope.launch {
        val list = getVodListCoUseCase.run(EmptyRequest())
        DLog.e("network end")
        handleVodList(list)
        _isLoading.value = false
    }
    //    fun loadVodListCo() = viewModelScope.launch(exceptionHandler) {
//        val list = getVodListCoUseCase.run(EmptyRequest())
//        DLog.e("network end")
//        handleVodList(list)
//        _isLoading.value = false
//    }

}