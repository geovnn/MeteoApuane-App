package com.geovnn.meteoapuane.presentation.webcam.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
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
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        ) {
            if (image!=null) {
                Column {
                    ImageCoil(
                        modifier = Modifier
                            .clickable { onClick(image) }
                            .fillMaxSize(),
                        url = image,
                        contentDescription = title?:"",
                        contentScale = ContentScale.FillWidth
                    )
                    if (title != null) {
                        AutoResizeText(
                            text = title,
                            fontSizeRange = FontSizeRange(1.sp,20.sp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1
                        )
//                    Text(text = title)
                    }
                    if (subtitle != null) {
                        AutoResizeText(
                            text = subtitle,
                            fontSizeRange = FontSizeRange(1.sp,20.sp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 2
                        )
//                    Text(text = subtitle)
                    }
                }

            } else {
                Column {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                    if (title != null) {
                        AutoResizeText(
                            text = title,
                            fontSizeRange = FontSizeRange(1.sp,20.sp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1
                        )
//                    Text(text = title)
                    }
                    if (subtitle != null) {
                        AutoResizeText(
                            text = subtitle,
                            fontSizeRange = FontSizeRange(1.sp,20.sp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 2
                        )
//                    Text(text = subtitle)
                    }
                }

            }
        }

    }

}