package com.videostreamingapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.exoplayer2.ui.PlayerView
import com.videostreamingapp.databinding.FragmentVideosBinding
import com.videostreamingapp.databinding.ItemVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment() : Fragment(), VideosAdapter.Listener {

    private val viewModel: VideosViewModel by viewModels()
    private lateinit var binding: FragmentVideosBinding
    private lateinit var listener: VideosAdapter.Listener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener = this
        val adapter = VideosAdapter(viewModel.videoLinks, listener)
        binding.viewPager.adapter = adapter
    }

    override fun onPause(binding: ItemVideoBinding) {
        binding.exoplayerView.player!!.playWhenReady = false
    }

    override fun onResume(binding: ItemVideoBinding) {
        binding.exoplayerView.player?.playWhenReady = true
    }
}