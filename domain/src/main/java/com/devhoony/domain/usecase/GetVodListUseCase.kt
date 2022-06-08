package com.devhoony.domain.usecase

import com.devhoony.domain.entity.Vod
import com.devhoony.domain.repository.VodRepository
import com.devhoony.domain.usecase.interactor.UseCase
import com.devhoony.domain.usecase.interactor.UseCase.None
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVodListUseCase
@Inject constructor(
    private val repository: VodRepository
) : UseCase<List<Vod>, None>() {
    override suspend fun run(params: None): Either<Failure, List<Vod>> = repository.getVodList()
}