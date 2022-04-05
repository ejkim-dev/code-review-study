package com.example.domain.usecase

import com.example.domain.entity.Vod
import com.example.domain.repository.VodRepository
import com.example.domain.usecase.GetVodUseCase.Params
import com.example.domain.usecase.interactor.UseCase
import com.example.domain.util.exception.Failure
import com.example.domain.util.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVodUseCase
@Inject constructor(
    private val repository: VodRepository
) : UseCase<Vod, Params>() {

    data class Params(val id: Int)

    override suspend fun run(params: Params): Either<Failure, Vod> = repository.getVod(params.id)
}


