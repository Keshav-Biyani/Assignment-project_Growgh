package com.keshav.internproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.white),this.theme)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        binding?.btnUpload?.setOnClickListener{
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        binding?.btnFeed?.setOnClickListener{
            val intentFeed = Intent(this, FeedActivity::class.java)
            startActivity(intentFeed)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}