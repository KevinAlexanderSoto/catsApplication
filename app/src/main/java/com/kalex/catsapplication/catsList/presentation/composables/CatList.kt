package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.layout.Column
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
import com.kalex.catsapplication.catsList.models.CatItemDto

@Composable
fun CatListColum(
    catList: List<CatItemDto>,
) {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "Cats Breeds",
            fontSize = 20.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp, 8.dp).align(Alignment.CenterHorizontally),
        )
        LazyColumn() {
            items(catList.size) {
                CatItemCard(
                    catList[it],
                    catList[it].imgUrl,
                )
            }
        }
    }
}
