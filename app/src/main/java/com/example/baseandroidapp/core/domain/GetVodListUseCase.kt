package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.*
import com.example.baseandroidapp.core.interactor.SingleUseCase
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class GetVodListUseCase
@Inject constructor(
    private val repository: VodRepository,
) : SingleUseCase<EmptyRequest, List<Vod>>() {
    override fun buildUseCaseSingle(): Single<List<Vod>> = repository.getVodList()
}