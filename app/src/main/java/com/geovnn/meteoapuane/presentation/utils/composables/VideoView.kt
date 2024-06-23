package com.geovnn.meteoapuane.presentation.utils.composables

import android.provider.MediaStore.Audio.Media
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_ALWAYS


@OptIn(UnstableApi::class)
@Composable
fun VideoView(
    modifier: Modifier = Modifier,
    videoUri: String,
    onClick: (String) -> Unit ,
) {
    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    val mediaSource = remember(videoUri) {
        MediaItem.fromUri(videoUri)
    }



    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        exoPlayer.playWhenReady=true
        exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
//                this.setShutterBackgroundColor(Color.Transparent.toArgb())
                this.background
                setShowBuffering(SHOW_BUFFERING_ALWAYS)
//                setShowBuffering()
                useController = false
                player = exoPlayer
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onClick(videoUri)
            }
//            .height(200.dp) // Set your desired height
    )
}