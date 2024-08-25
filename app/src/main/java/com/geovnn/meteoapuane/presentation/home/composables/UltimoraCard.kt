package com.geovnn.meteoapuane.presentation.home.composables

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun UltimoraCard(
    image: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.outlinedCardColors(
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            ) {
                if (image != "") {
                    ImageCoil(
                        url = image,
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
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle
                )
            }
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = body,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
            )
        }
    }
}