package com.geovnn.meteoapuane.presentation.utils.composables

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.Decoder
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
    AsyncImage(
        modifier=modifier,
        model = if (SDK_INT >= 28) {
            ImageRequest.Builder(LocalContext.current)
                .data(url)
                .decoderFactory(ImageDecoderDecoder.Factory())
                .decoderFactory(GifDecoder.Factory())
                .build()
        } else {
            ImageRequest.Builder(LocalContext.current)
                .data(url)
                .decoderFactory(GifDecoder.Factory())
                .build()
        },
        contentDescription = contentDescription,
        contentScale = contentScale
    )

}