package com.example.baseandroidapp.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidapp.core.data.EmptyRequest
import com.example.baseandroidapp.core.domain.GetVodListCoUseCase
import com.example.baseandroidapp.core.domain.GetVodListUseCase
import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.util.DLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VodViewModel
@Inject constructor(
    private val getVodListUseCase: GetVodListUseCase,
    private val getVodListCoUseCase: GetVodListCoUseCase
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


    fun loadVodListCo() {
        _isLoading.value = true
        DLog.e("vod")
        viewModelScope.launch {
            try {
                val list = getVodListCoUseCase.run(EmptyRequest())
                handleVodList(list)

            } catch (e: Exception) {
                DLog.e("network error")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

}