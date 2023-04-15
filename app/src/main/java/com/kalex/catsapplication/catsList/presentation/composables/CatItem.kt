package com.kalex.catsapplication.catsList.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalex.catsapplication.catsList.models.CatItemDto

@Composable
fun CatItemCard(
    cat: CatItemDto,
    imgUrl: String,
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(360.dp)
            .fillMaxWidth()
            .clickable { },
    ) {
        Text(
            text = cat.name,
            fontSize = 18.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(2.dp, 8.dp).align(Alignment.CenterHorizontally),
        )

        AsyncImage(
            model = imgUrl,
            contentDescription = "cat img",
            modifier = Modifier.padding(2.dp).height(225.dp).width(250.dp).align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp).align(Alignment.CenterHorizontally), // intrinsic measurements
            horizontalArrangement = Arrangement.Center,
        ) {
            CatProperty("Pais", cat.origin)

            CatProperty("Inteligencia", cat.intelligence.toString())
        }
    }
}

@Composable
fun CatProperty(title: String, subtitle: String) {
    Column(
        modifier = Modifier.padding(2.dp),
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp, 4.dp).align(Alignment.CenterHorizontally),
        )
        Text(
            text = subtitle,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp, 2.dp).align(Alignment.CenterHorizontally),
        )
    }
}
