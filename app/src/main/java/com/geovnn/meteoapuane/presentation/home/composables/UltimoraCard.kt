package com.geovnn.meteoapuane.presentation.home.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UltimoraCard(
    image: Bitmap?,
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            ) {
                if (image != null) {
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight(),
                        //                        .weight(1f) ,
                        contentScale = ContentScale.FillHeight
                    )
                }
                Text(
                    text = title,
                    modifier= Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.onTertiaryContainer

                )
            }
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = body,
//        fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onTertiaryContainer

            )
        }
    }

}