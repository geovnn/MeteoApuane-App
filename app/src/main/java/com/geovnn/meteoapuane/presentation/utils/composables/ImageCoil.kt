package com.geovnn.meteoapuane.presentation.utils.composables

import android.os.Build.VERSION.SDK_INT
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun ImageCoil(
    modifier : Modifier = Modifier,
    url: String,
    contentDescription: String = "",
    contentScale : ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .build()
    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .apply {
            if (SDK_INT >= 28) {
                decoderFactory(ImageDecoderDecoder.Factory())
                decoderFactory(GifDecoder.Factory())
                setHeader("User-Agent", "Mozilla/5.0")
                .crossfade(true)
            } else {
                decoderFactory(GifDecoder.Factory())
                setHeader("User-Agent", "Mozilla/5.0")
                .crossfade(true)
            }
        }
        .build()
    val painter: Painter = rememberVectorPainter(image = Icons.Default.ImageNotSupported)
    AsyncImage(
        modifier=modifier,
        imageLoader = imageLoader,
        model = imageRequest,
        error = painter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}