package com.example.domain.usecase

import com.example.domain.entity.Vod
import com.example.domain.repository.VodRepository
import com.example.domain.usecase.interactor.UseCase
import com.example.domain.usecase.interactor.UseCase.None
import com.example.domain.util.exception.Failure
import com.example.domain.util.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVodListUseCase
@Inject constructor(
    private val repository: VodRepository
) : UseCase<List<Vod>, None>() {
    override suspend fun run(params: None): Either<Failure, List<Vod>> = repository.getVodList()
}