package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.*
import com.example.baseandroidapp.core.interactor.SingleRxUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVodListRxUseCase
@Inject constructor(
    private val repository: VodRepository,
) : SingleRxUseCase<EmptyRequest, List<Vod>>() {
    override fun buildUseCaseSingle(): Single<List<Vod>> = repository.getVodListRx()
}