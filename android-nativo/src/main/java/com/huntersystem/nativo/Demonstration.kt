package com.huntersystem.nativo

import android.media.MediaPlayer
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
internal fun Demonstration(id: String) {
    val context = LocalContext.current
    val available = remember(context) { context.assets.list("").orEmpty().toSet() }
    if ("$id.mp4" !in available) return
    key(id) {
        var player by remember { mutableStateOf<MediaPlayer?>(null) }
        var playing by remember { mutableStateOf(false) }
        fun release() {
            player?.release()
            player = null
            playing = false
        }
        DisposableEffect(Unit) { onDispose { release() } }
        AndroidView(factory = { activity ->
            SurfaceView(activity).apply {
                holder.addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(surface: SurfaceHolder) {
                        val next = MediaPlayer()
                        try {
                            activity.assets.openFd("$id.mp4").use { next.setDataSource(it.fileDescriptor, it.startOffset, it.length) }
                            next.setDisplay(surface)
                            next.isLooping = true
                            next.setOnPreparedListener { if (player === it) it.seekTo(0) }
                            next.setOnErrorListener { _, _, _ -> release(); true }
                            next.prepare()
                            player = next
                        } catch (error: Exception) { next.release() }
                    }
                    override fun surfaceChanged(surface: SurfaceHolder, format: Int, width: Int, height: Int) = Unit
                    override fun surfaceDestroyed(surface: SurfaceHolder) = release()
                })
            }
        }, modifier = Modifier.fillMaxWidth().aspectRatio(1280f / 1422))
        TextButton(enabled = player != null, onClick = {
            player?.let {
                if (it.isPlaying) it.pause() else it.start()
                playing = it.isPlaying
            }
        }) { Text(if (playing) "Pausar demostración" else "Reproducir demostración") }
    }
}
