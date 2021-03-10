package com.videostreamingapp.ui.main

import androidx.lifecycle.ViewModel
import com.videostreamingapp.data.repositories.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    repository: VideoRepository
) : ViewModel() {

    val videoLinks = repository.getVideoLinks()
}
