package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kalex.catsapplication.catsList.presentation.viewmodel.CatsListPagingViewModel
import com.kalex.catsapplication.catsList.presentation.viewmodel.ListState
import kotlinx.coroutines.launch

@Composable
fun PagingCatListColum2(
    screenTitle: String,
) {
    val viewModel = hiltViewModel<CatsListPagingViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            viewModel.getCats()
        }
    }
    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (
                lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: -9
                ) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    val articles = viewModel.newsList

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE) {
            viewModel.getCats()
        }
    }

    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = screenTitle,
            fontSize = 20.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .align(Alignment.CenterHorizontally),
        )
        LazyColumn(state = lazyColumnListState) {
            itemsIndexed(
                items = articles,
                key = { index, it -> it.id },
            ) { idex, cat ->
                cat?.let { it1 ->
                    CatItemCard(
                        it1,
                        cat.imgUrl,
                    )
                }
            }

            item(
                key = viewModel.listState,
            ) {
                when (viewModel.listState) {
                    ListState.LOADING -> {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            LoadingBar(true)
                        }
                    }
                    ListState.PAGINATING -> {
                    }
                    ListState.PAGINATION_EXHAUST -> {
                    }
                    else -> {}
                }
            }
        }
    }
}
