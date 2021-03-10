package com.videostreamingapp.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.videostreamingapp.R
import com.videostreamingapp.databinding.ItemVideoBinding


class VideosAdapter(private var itemList: ArrayList<String> = ArrayList()) :
    RecyclerView.Adapter<VideosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.initializePlayer(itemList[position])

    override fun getItemCount(): Int = itemList.size

    inner class MyViewHolder(private val itemVideoBinding: ItemVideoBinding) :
        RecyclerView.ViewHolder(itemVideoBinding.root) {
        private lateinit var simpleExoPlayer: SimpleExoPlayer
        private val context = itemVideoBinding.root.context
        private val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(context, "exoplayer")

        private fun buildMediaSource(uri: Uri): MediaSource {
            return ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }

        fun initializePlayer(videoUrl: String) {
            simpleExoPlayer = SimpleExoPlayer.Builder(context).build()
            preparePlayer(videoUrl)
            simpleExoPlayer.playWhenReady = true
            simpleExoPlayer.addListener(this)
            itemVideoBinding.exoplayerView.player = simpleExoPlayer
        }

        private fun preparePlayer(videoUrl: String) {
            val uri = Uri.parse(videoUrl)
            val mediaSource = buildMediaSource(uri)
            simpleExoPlayer.setMediaSource(mediaSource)
        }
    }
}