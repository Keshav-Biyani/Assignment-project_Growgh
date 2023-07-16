package com.keshav.internproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import com.keshav.internproject.Model.ContantModel
import com.keshav.internproject.Object.Contants
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private var binding : ActivityStartBinding? = null
    private var currentPossition : Int = 1
    private var list = ArrayList<ContantModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.white),this.theme)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
       // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val shareedPref = getSharedPreferences("Pref",Context.MODE_PRIVATE)
        val startComplete = shareedPref.getBoolean("startComplete",false)

        if(startComplete){
                startActivity()
        }else{
            binding?.readyBtn?.setOnClickListener{
                shareedPref.edit().putBoolean("startComplete",true).apply()
                startActivity()
            }
        }
        list  = Contants.getContantsDetails()
        startActivityPostion()
        binding?.flSkip?.setOnClickListener{
            //Toast.makeText(this,"Skip",Toast.LENGTH_SHORT).show()
            shareedPref.edit().putBoolean("startComplete",true).apply()
            startActivity()
        }
        binding?.llForwardBtn?.setOnClickListener {
            if(currentPossition<= list.size){
                currentPossition++
                startActivityPostion()
            }
        }

    }
   private fun startActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun startActivityPostion(){
        val mlist : ContantModel = list[currentPossition-1]
        binding?.progressBar?.progress = currentPossition
        binding?.introImage?.setImageResource(mlist.image)
        binding?.introTitle?.text = mlist.title
        if(currentPossition == list.size){
            binding?.progressBar?.visibility = View.INVISIBLE
            binding?.llForwardBtn?.visibility = View.INVISIBLE
            binding?.readyBtn?.visibility = View.VISIBLE
        }else{
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.llForwardBtn?.visibility = View.VISIBLE
            binding?.readyBtn?.visibility = View.INVISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}