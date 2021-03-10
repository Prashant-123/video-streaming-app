package com.videostreamingapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.videostreamingapp.R
import com.videostreamingapp.databinding.ActivitySplashScreenBinding
import com.videostreamingapp.utils.CommonValues

class SplashScreenActivity : AppCompatActivity() {
    private var binding : ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_splash_screen, null, false)
        setContentView(binding!!.root)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }, CommonValues.SPLASH_TIME)
    }
}