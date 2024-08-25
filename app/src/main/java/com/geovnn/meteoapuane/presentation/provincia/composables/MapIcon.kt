package com.geovnn.meteoapuane.presentation.provincia.composables

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun MapIcon(
    modifier: Modifier = Modifier,
    image: String,
    offsetX: Dp,
    offsetY: Dp,
    size: Dp,
) {
    ImageCoil(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .absoluteOffset(offsetX - size / 2, offsetY - size / 2),
        url = image,
        contentDescription = ""
    )
}