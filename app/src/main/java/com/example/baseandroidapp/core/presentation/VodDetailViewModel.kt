package com.example.baseandroidapp.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseandroidapp.core.data.VodRequest
import com.example.baseandroidapp.core.domain.GetVodUseCase
import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.util.DLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VodDetailViewModel
@Inject constructor(
    private val getVodUseCase: GetVodUseCase
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _vodData: MutableLiveData<VodDetailView> = MutableLiveData()
    val vodData: LiveData<VodDetailView> = _vodData

    fun loadVodData(id:Int) {
        _isLoading.value = true
        DLog.e("vod")
        getVodUseCase.setRequestParams(VodRequest(id))
        getVodUseCase.execute(
            onSuccess = {
                DLog.e("handleVodData: $it")
                handleVodData(it)
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

    private fun handleVodData(model: Vod) {
        _vodData.value = VodDetailView(model.id, model.title, model.description, model.imageUrl)
    }

}