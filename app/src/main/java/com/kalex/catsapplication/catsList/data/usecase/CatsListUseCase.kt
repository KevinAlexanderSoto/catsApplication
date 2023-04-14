package com.kalex.catsapplication.catsList.data.usecase

import com.kalex.catsapplication.catsList.data.repository.CatsListRespository
import com.kalex.catsapplication.catsList.models.CatList
import com.kalex.catsapplication.utils.UseCaseFlowStatus
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatsListUseCase @Inject constructor(
    private val repository: CatsListRespository,
) {
    fun getCatsBreeds() = flow<UseCaseFlowStatus<CatList>> {
        try {
            emit(UseCaseFlowStatus.Loading("Loading"))
            val catList = repository.getCatsBreeds()
            emit(UseCaseFlowStatus.Success(catList))
        } catch (e: Exception) {
            emit(UseCaseFlowStatus.Error("Unknown error"))
        }
    }
}
