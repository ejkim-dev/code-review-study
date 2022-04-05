package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Vod
import com.example.domain.usecase.GetVodUseCase
import com.example.domain.usecase.GetVodUseCase.Params
import com.example.presentation.base.BaseViewModel
import com.example.presentation.view.VodDetailView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VodDetailViewModel
 @Inject constructor(
    private val getVodUseCase: GetVodUseCase
) : BaseViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _vodData: MutableLiveData<VodDetailView> = MutableLiveData()
    val vodData: LiveData<VodDetailView> = _vodData

    fun loadVodData(id: Int) {
        _isLoading.value = true
//        DLog.e("vod")

        getVodUseCase(Params(id), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleVodData
            )
            _isLoading.value = false
        }

    }

    private fun handleVodData(model: Vod) {
        _vodData.value = VodDetailView(model.id, model.title, model.description, model.imageUrl)
    }

}