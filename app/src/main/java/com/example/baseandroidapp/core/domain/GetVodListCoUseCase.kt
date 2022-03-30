package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.*
import com.example.baseandroidapp.core.interactor.CoUseCase
import com.example.baseandroidapp.core.interactor.SingleUseCase
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class GetVodListCoUseCase
@Inject constructor(
    private val repository: VodRepository,
) : CoUseCase<EmptyRequest, List<Vod>>() {
    override suspend fun run(params: EmptyRequest): List<Vod> = repository.getVodListCo()
}