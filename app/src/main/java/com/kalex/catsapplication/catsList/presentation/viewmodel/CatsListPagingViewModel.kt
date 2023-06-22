package com.kalex.catsapplication.catsList.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kalex.catsapplication.catsList.data.repository.CatsListRepository
import com.kalex.catsapplication.catsList.models.CatItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsListPagingViewModel @Inject constructor(
    private val repository: CatsListRepository,
) : ViewModel() {
    val newsList = mutableStateListOf<CatItemDto>()

    private var page by mutableStateOf(0)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)
    suspend fun getCats() {
        if (page == 0 || (page != 0 && canPaginate) && listState == ListState.IDLE) {
            listState = if (page == 0) ListState.LOADING else ListState.PAGINATING
            try {
                val result = repository.getPagingCatsBreeds(page)

                canPaginate = result.size == 10

                if (page == 1) {
                    newsList.clear()
                    newsList.addAll(result)
                } else {
                    newsList.addAll(result)
                }

                listState = ListState.IDLE

                if (canPaginate) {
                    page++
                }
            } catch (e: Exception) {
                listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
            }
        }
    }

    fun clearPaging() {
        page = 0
        listState = ListState.IDLE
        canPaginate = false
    }
}

enum class ListState {
    IDLE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
}
