package com.kalex.catsapplication.catsList.data.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kalex.catsapplication.catsList.data.paging.CatsPagingSource
import com.kalex.catsapplication.catsList.data.repository.CatsListRepository
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.utils.UseCaseFlowStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatsListUseCase @Inject constructor(
    private val repository: CatsListRepository,
) {
    fun getCatsBreeds() = flow<UseCaseFlowStatus<List<CatItemDto>>> {
        try {
            emit(UseCaseFlowStatus.Loading("Loading"))
            coroutineScope {
                val catList = async(Dispatchers.IO) {
                    repository.getCatsBreeds()
                }
                catList.await().map {
                    it.imgUrl = repository.getCatImage(it.imgId ?: "NZpO4pU56M")
                }
                emit(UseCaseFlowStatus.Success(catList.await()))
            }
        } catch (e: Exception) {
            emit(UseCaseFlowStatus.Error("Unknown error"))
        }
    }
    fun getPagingCatsBreeds(): Flow<PagingData<CatItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                CatsPagingSource(repository)
            },
        ).flow
    }
}
