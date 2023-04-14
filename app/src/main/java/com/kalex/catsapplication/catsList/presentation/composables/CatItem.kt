package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalex.catsapplication.catsList.models.CatItem

@Composable
fun CatItemCard(
    cat: CatItem,
    imgUrl: String,
) {
    // TODO : Adjust design
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .height(110.dp)
            .fillMaxWidth()
            .clickable { },
    ) {
        Text(
            text = cat.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp, 1.dp),
        )

        AsyncImage(
            model = imgUrl,
            contentDescription = "cat img",
            modifier = Modifier.padding(2.dp).fillMaxSize(),
            contentScale = ContentScale.Fit,
        )
        Row(
            Modifier
                .height(IntrinsicSize.Min)
                .padding(24.dp), // intrinsic measurements
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = cat.origin,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp, 1.dp),
            )
            Text(
                text = cat.intelligence.toString(),
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp, 1.dp),
            )
        }
    }
}
