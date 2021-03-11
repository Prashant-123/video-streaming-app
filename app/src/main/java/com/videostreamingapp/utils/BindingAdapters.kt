package com.videostreamingapp.utils

import android.content.Context
import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("video_url", "on_state_change")
    fun PlayerView.loadVideo(url: String, callback: PlayerStateCallback) {
        val player = SimpleExoPlayer.Builder(context).build()

        player.playWhenReady = true
        this.useController = true
        val mediaSource = ProgressiveMediaSource.Factory(CacheDataSourceFactory(VideoCache.get(context), DefaultDataSourceFactory(context, "ua"))).createMediaSource(Uri.parse(url))

        mediaSource?.let { player.prepare(it, true, false) }
        this.player = player
        player.repeatMode = Player.REPEAT_MODE_ONE
        this.player!!.addListener(object : Player.EventListener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)

                if (playbackState == Player.STATE_BUFFERING) callback.onVideoBuffering(player) // Buffering.. set progress bar visible here
                if (playbackState == Player.STATE_READY){
                    callback.onVideoDurationRetrieved(((this@loadVideo.player as SimpleExoPlayer).duration), player)
                }
                if (playbackState == Player.STATE_READY && player.playWhenReady){
                    callback.onStartedPlaying(player)
                }
            }
        })
    }

    object VideoCache {
        private var downloadCache: SimpleCache? = null
        private const val maxCacheSize: Long = 20 * 1024 * 1024
        private const val dirName: String = "media"

        fun get(context : Context) : SimpleCache{
            if (downloadCache == null) {
                val cacheFolder = File(context.cacheDir, dirName)
                val cacheEvictor = LeastRecentlyUsedCacheEvictor(maxCacheSize)
                downloadCache = SimpleCache(cacheFolder, cacheEvictor)
            }
            return downloadCache as SimpleCache
        }
    }
}