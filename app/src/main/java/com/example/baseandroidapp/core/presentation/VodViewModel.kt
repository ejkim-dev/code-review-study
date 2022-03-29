package com.example.baseandroidapp.core.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseandroidapp.core.data.VodEntity
import com.example.baseandroidapp.core.domain.GetVodListUseCase
import com.example.baseandroidapp.core.domain.GetVodUseCase
import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.util.DLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VodViewModel
@Inject constructor(
    private val getVodListUseCase: GetVodListUseCase
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _vodList: MutableLiveData<List<VodView>> = MutableLiveData()
    val vodList: LiveData<List<VodView>> = _vodList

    fun loadVodList() {
        _isLoading.value = true
        DLog.e("vod")
        getVodListUseCase.execute(
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

}