package com.kalex.catsapplication.catsList.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kalex.catsapplication.catsList.data.repository.CatsListRepository
import com.kalex.catsapplication.catsList.models.CatItemDto

class CatsPagingSource(
    private val repository: CatsListRepository,
) : PagingSource<Int, CatItemDto>() {
    override fun getRefreshKey(state: PagingState<Int, CatItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatItemDto> {
        return try {
            val page = params.key ?: 0
            val response = repository.getPagingCatsBreeds(page = page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
