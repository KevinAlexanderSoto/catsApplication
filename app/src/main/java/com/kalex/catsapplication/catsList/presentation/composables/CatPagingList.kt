package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.kalex.catsapplication.catsList.models.CatItemDto

@Composable
fun PagingCatListColum(
    catList: LazyPagingItems<CatItemDto>,
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = "Cats Breeds",
            fontSize = 20.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .align(Alignment.CenterHorizontally),
        )
        LazyColumn() {
            items(
                items = catList,
                key = { it.id },
            ) { cat ->
                cat?.let { it1 ->
                    CatItemCard(
                        it1,
                        cat.imgUrl,
                    )
                }
            }
            when (catList.loadState.refresh) { // FIRST LOAD
                is LoadState.Error -> {
                    // TODO Add Error Item
                    // state.error to get error message
                }

                is LoadState.Loading -> { // Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            LoadingBar(true)
                        }
                    }
                }

                else -> {}
            }

            when (catList.loadState.append) { // Pagination
                is LoadState.Error -> {
                    // TODO Pagination Error Item
                    // state.error to get error message
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            LoadingBar(true)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}
