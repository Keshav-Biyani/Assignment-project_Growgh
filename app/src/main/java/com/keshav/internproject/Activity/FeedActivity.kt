package com.keshav.internproject.Activity

import android.app.AlertDialog
import android.app.DownloadManager.Request
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.service.voice.VoiceInteractionSession
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.keshav.internproject.Adapter.FeedAdapter
import com.keshav.internproject.Model.FeedModel
import com.keshav.internproject.R

import com.keshav.internproject.databinding.ActivityFeedBinding
import com.keshav.internproject.utlies.ConnectionManager
import com.keshav.internproject.utlies.SwipeShareCallBack
import org.json.JSONException

class FeedActivity : AppCompatActivity() {
    private var binding : ActivityFeedBinding? = null
    private var listfeed = arrayListOf<FeedModel>()
    private var feedAdapter : FeedAdapter? = null
    private var page : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.statusFeed),this.theme)
        apirequest()
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.swipeRefreshLayout?.isRefreshing = true
            listfeed.clear()
            newapirequest()

        }
        swiptoGesture(binding?.rvFeedPost)
}
private fun apirequest(){

    if (ConnectionManager().checkConectivity(applicationContext)) {
        val queue = Volley.newRequestQueue(applicationContext)
        val url = "https://api.pexels.com/v1/curated/?page=${page}&per_page=10"
        val jsonObjectRequest =  object :JsonObjectRequest(
            Method.GET,url,null,Response.Listener
            {
                try {
                    val jsonArray = it.getJSONArray("photos")
                    val length = jsonArray.length()
                   // Log.e("Test","Print the ${length}")
                    for( i in 0..length){
                        val jsonobj = jsonArray.getJSONObject(i)
                        val id = jsonobj.getInt("id")
                        val objectimage = jsonobj.getJSONObject("src")
                        val urlimage = objectimage.getString("portrait")
                        val feedObject = FeedModel(
                            id,
                            urlimage)
                        listfeed.add(feedObject)
                        //  println(listfeed!![0])
                        binding?.rvFeedPost?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                        feedAdapter = FeedAdapter(this, listfeed)
                        binding?.rvFeedPost?.adapter = feedAdapter

                    }
                    swiptoGesture(binding?.rvFeedPost)

                }
                catch (e: JSONException) {
                  //  Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }, Response.ErrorListener
            {

               // Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "ra96CqddNkqWFtU66l7wM9DjIAPD1fRcczTtdCVj7JJm5kT67WcyD5Dp"
                return headers
            }
        }
        queue.add(jsonObjectRequest)


    }
    else{
       showAlertDialog()
    }
}
    private fun newapirequest(){
page++
        if (ConnectionManager().checkConectivity(applicationContext)) {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = "https://api.pexels.com/v1/curated/?page=${page}&per_page=10"
            val jsonObjectRequest =  object :JsonObjectRequest(
                Method.GET,url,null,Response.Listener
                {
                    try {
                        val jsonArray = it.getJSONArray("photos")
                        val length = jsonArray.length()
                       // Log.e("Test","Print the ${length}")
                        for( i in 0..length){
                            val jsonobj = jsonArray.getJSONObject(i)
                            val id = jsonobj.getInt("id")
                            val objectimage = jsonobj.getJSONObject("src")
                            val urlimage = objectimage.getString("portrait")
                            val feedObject = FeedModel(
                                id,
                                urlimage)
                            listfeed.add(feedObject)
                            //  println(listfeed!![0])
                            binding?.rvFeedPost?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                            feedAdapter = FeedAdapter(this, listfeed)
                            binding?.rvFeedPost?.adapter = feedAdapter

                        }
                        swiptoGesture(binding?.rvFeedPost)


                    }
                    catch (e: JSONException) {
                        //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                    binding?.swipeRefreshLayout?.isRefreshing = false
                }, Response.ErrorListener
                {

                  //  Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = "ra96CqddNkqWFtU66l7wM9DjIAPD1fRcczTtdCVj7JJm5kT67WcyD5Dp"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)


        }
        else{
            showAlertDialog()
        }
    }
    private fun showAlertDialog(){
        AlertDialog.Builder(this@FeedActivity).setMessage(
            "You have not connected with Internet "
        )
            .setPositiveButton("go To Setting") { _, _ ->
                try {
                    val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(settingIntent)

                }catch (e : Exception){
                    e.printStackTrace()

                }
            }
            .setNegativeButton("Cancel"){
                    dialog,_->
                dialog.dismiss()
            }.show()
    }
    fun swiptoGesture(itemRV : RecyclerView?){
        val swipeGesture = object :SwipeShareCallBack(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                try{
                    when(direction){
                        ItemTouchHelper.RIGHT->{
                            Log.e("run swipe","Heel")



                        }
                    }
                }catch (e : Exception){

                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(itemRV)

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}