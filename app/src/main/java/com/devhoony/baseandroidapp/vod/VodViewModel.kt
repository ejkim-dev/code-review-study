package com.devhoony.baseandroidapp.vod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devhoony.baseandroidapp.util.base.BaseViewModel
import com.devhoony.domain.entity.Vod
import com.devhoony.domain.usecase.GetVodListUseCase
import com.devhoony.domain.usecase.interactor.UseCase.None
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VodViewModel
 @Inject constructor(
    private val getVodListUseCase: GetVodListUseCase,
) : BaseViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _vodList: MutableLiveData<List<VodView>> = MutableLiveData()
    val vodList: LiveData<List<VodView>> = _vodList

    fun loadVodList(){
        _isLoading.value = true
//        DLog.e("vod")
        getVodListUseCase(None(),viewModelScope){
            it.fold(
             ::handleFailure,
             ::handleVodList
            )
        }
        _isLoading.value = false
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

}