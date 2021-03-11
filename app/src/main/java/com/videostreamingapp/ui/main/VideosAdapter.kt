package com.videostreamingapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.videostreamingapp.R
import com.videostreamingapp.databinding.ItemVideoBinding
import com.videostreamingapp.utils.PlayerStateCallback


class VideosAdapter(private var itemList: ArrayList<String> = ArrayList(), private var listener: Listener) :
    RecyclerView.Adapter<VideosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_video, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        listener.onPause(holder.binding)

    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        listener.onResume(holder.binding)
    }

    override fun getItemCount(): Int = itemList.size

    class MyViewHolder(var binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root), PlayerStateCallback {
        override fun onVideoDurationRetrieved(duration: Long, player: Player) {

        }

        override fun onVideoBuffering(player: Player) {
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onStartedPlaying(player: Player) {
            binding.progressBar.visibility = View.GONE
        }

        override fun onFinishedPlaying(player: Player) {
        }

        fun bind(videoUrl: String) {
            with(binding) {
                url = videoUrl
                listener = this@MyViewHolder
            }
        }
    }

    interface Listener {
        fun onPause(binding: ItemVideoBinding)
        fun onResume(binding: ItemVideoBinding)
    }
}