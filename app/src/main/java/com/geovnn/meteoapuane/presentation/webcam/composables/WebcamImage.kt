package com.geovnn.meteoapuane.presentation.webcam.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun WebcamImage(
    modifier: Modifier = Modifier,
    image: String,
    title: String?,
    subtitle: String?,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier.padding(2.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        ) {
            Column(
            ) {
                ImageCoil(
                    modifier = Modifier
                        .clickable { onClick(image) }
                        .fillMaxSize(),
                    url = image,
                    contentDescription = title?:"",
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier.padding(5.dp)

                ) {
                    if (title != null) {
                        Text(
                            text = title,
                            maxLines = 10,
                            fontSize = 20.sp
                        )
                    }
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            maxLines = 10,
                        )
                    }
                }

            }
        }
    }
}