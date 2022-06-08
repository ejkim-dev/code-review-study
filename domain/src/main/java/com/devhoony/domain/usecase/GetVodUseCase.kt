package com.devhoony.domain.usecase

import com.devhoony.domain.entity.Vod
import com.devhoony.domain.repository.VodRepository
import com.devhoony.domain.usecase.GetVodUseCase.Params
import com.devhoony.domain.usecase.interactor.UseCase
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
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


