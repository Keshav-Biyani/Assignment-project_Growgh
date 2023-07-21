package com.keshav.internproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.white),this.theme)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val titleName = sharedPreferences.getString("Title","The Avengers")
        val string = "Welcome $titleName"
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        binding?.headline?.text =string
        binding?.flLogOut?.setOnClickListener{
            sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding?.btnUpload?.setOnClickListener{
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        binding?.btnFeed?.setOnClickListener{
            val intentFeed = Intent(this, HomeActivity::class.java)
            startActivity(intentFeed)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}