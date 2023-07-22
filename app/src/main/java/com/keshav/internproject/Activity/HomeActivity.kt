package com.keshav.internproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
    private lateinit var navHostFragment: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //setcurrFragement(R.id.home)
        window.statusBarColor = resources.getColor((R.color.statusFeed),this.theme)
        navHostFragment = findViewById(R.id.nav_host_fragment)
        val navController = findNavController(R.id.nav_host_fragment)
        binding?.bottomNavigation?.setupWithNavController(navController)
        binding?.bottomNavigation?.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home->{
                    setcurrFragement(R.id.feedFragment)
                    binding?.bottomNavigation?.visibility = View.VISIBLE
                    //binding?.fabUploadImage?.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.video->{
                    setcurrFragement(R.id.videoFragment)
                    binding?.bottomNavigation?.visibility = View.GONE
                  //  binding?.fabUploadImage?.visibility = View.GONE
                    return@setOnItemSelectedListener true

                }
                R.id.map->{
                    setcurrFragement(R.id.mapFragment)
                    binding?.bottomNavigation?.visibility = View.GONE
                   // binding?.fabUploadImage?.visibility = View.GONE
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

    }
    private fun setcurrFragement(veiwId : Int){
        val navController = navHostFragment.findNavController()
        navController.navigate(veiwId)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        binding?.bottomNavigation?.visibility = View.VISIBLE
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()

        }
    }


}