package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.VodRepository
import com.example.baseandroidapp.core.data.VodRequest
import com.example.baseandroidapp.core.interactor.SingleRxUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVodRxUseCase
@Inject constructor(
    private val repository: VodRepository
) : SingleRxUseCase<VodRequest, Vod>() {
    override fun buildUseCaseSingle(): Single<Vod> = repository.getVodRx(getRequestParams()!!)
}