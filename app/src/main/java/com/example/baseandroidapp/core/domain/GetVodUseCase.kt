package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.VodRepository
import com.example.baseandroidapp.core.data.VodRequest
import com.example.baseandroidapp.core.data.VodResponse
import com.example.baseandroidapp.core.interactor.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVodUseCase
@Inject constructor(
    private val repository: VodRepository
) : SingleUseCase<VodRequest, Vod>() {
    override fun buildUseCaseSingle(): Single<Vod> = repository.getVod(getRequestParams()!!)
}