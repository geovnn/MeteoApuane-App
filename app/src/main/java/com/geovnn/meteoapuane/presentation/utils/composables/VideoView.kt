package com.geovnn.meteoapuane.presentation.utils.composables

import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaSource = remember(videoUri) {
        MediaItem.fromUri(videoUri)
    }
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        exoPlayer.playWhenReady=true
        exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                this.background
                setShowBuffering(SHOW_BUFFERING_ALWAYS)
                useController = false
                player = exoPlayer
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onClick(videoUri)
            }
    )
}