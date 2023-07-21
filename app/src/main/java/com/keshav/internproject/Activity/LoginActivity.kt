package com.keshav.internproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private var binding : ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.statusFeed),this.theme)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if(isLoggedIn){
            val intent = Intent(this@LoginActivity, StartActivity::class.java)
            startActivity(intent)
            finish()
        }

            binding?.btnStart?.setOnClickListener {
                if(binding?.etName?.text.toString().isEmpty()){
                    Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show()
                }else {
                    val name = binding?.etName?.text.toString()

                    savePreferences(name)
                    val intent = Intent(this@LoginActivity, StartActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }


    }
    fun savePreferences(title: String){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply() //isLoggedIn is key
        sharedPreferences.edit().putString("Title",title).apply()
    }
}