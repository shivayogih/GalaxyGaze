package com.shivayogi.galaxygaze.ui.utils


import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlaybackException

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerOrThumbnail(url: String) {
    val context = LocalContext.current
    // Video playback
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    // Show a Toast message on player error
                    Toast.makeText(
                        context,
                        "Playback error: ${error.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
            setMediaItem(MediaItem.fromUri(Uri.parse(url)))
            prepare()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
                useController = true
                setShutterBackgroundColor(Color.BLACK)
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    DisposableEffect(context) {
        onDispose {
            player.release()
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}