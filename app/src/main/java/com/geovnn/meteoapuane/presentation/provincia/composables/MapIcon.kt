package com.geovnn.meteoapuane.presentation.provincia.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp

@Composable
fun MapIcon(
    modifier: Modifier = Modifier,
    image: Bitmap,
    offsetX: Dp,
    offsetY: Dp,
    size: Dp,
) {

    Image(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .absoluteOffset(offsetX - size / 2, offsetY - size / 2),
        bitmap = image.asImageBitmap(),
        contentDescription = ""
    )
}