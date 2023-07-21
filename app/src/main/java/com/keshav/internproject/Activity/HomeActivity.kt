package com.keshav.internproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityHomeBinding
import com.keshav.internproject.fragement.FeedFragment
import com.keshav.internproject.fragement.MapFragment
import com.keshav.internproject.fragement.VideoFragment

class HomeActivity : AppCompatActivity() {
    private var binding : ActivityHomeBinding? = null
    private val feedFragment = FeedFragment()
    private val videoFragment = VideoFragment()
    private val mapFragment = MapFragment()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setcurrFragement(feedFragment)
        window.statusBarColor = resources.getColor((R.color.statusFeed),this.theme)

        binding?.bottomNavigation?.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home->{
                    setcurrFragement(feedFragment)
                    binding?.bottomNavigation?.visibility = View.VISIBLE
                    //binding?.fabUploadImage?.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.video->{
                    setcurrFragement(videoFragment)
                    binding?.bottomNavigation?.visibility = View.GONE
                  //  binding?.fabUploadImage?.visibility = View.GONE
                    return@setOnItemSelectedListener true

                }
                R.id.map->{
                    setcurrFragement(mapFragment)
                    binding?.bottomNavigation?.visibility = View.GONE
                   // binding?.fabUploadImage?.visibility = View.GONE
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

    }
    private fun setcurrFragement(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flNav,fragment)
            commit()
        }
    }

}