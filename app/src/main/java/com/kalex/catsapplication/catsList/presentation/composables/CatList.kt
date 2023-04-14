package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.kalex.catsapplication.catsList.models.CatItem

@Composable
fun CatListColum(
    catList: List<CatItem>,
) {
    LazyColumn() {
        items(catList.size) {
            CatItemCard(
                catList[it],
                "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
            )
        }
    }
}
