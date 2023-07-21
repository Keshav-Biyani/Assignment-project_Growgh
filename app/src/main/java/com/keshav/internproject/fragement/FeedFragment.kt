package com.keshav.internproject.fragement

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaScannerConnection
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.keshav.internproject.Activity.UploadActivity
import com.keshav.internproject.Adapter.FeedAdapter
import com.keshav.internproject.Model.FeedModel
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityFeedBinding
import com.keshav.internproject.databinding.FragmentFeedBinding
import com.keshav.internproject.utlies.ConnectionManager
import com.keshav.internproject.utlies.SwipeShareCallBack
import org.json.JSONException


class FeedFragment : Fragment() {

    private var binding : FragmentFeedBinding?  = null
    private var listfeed = arrayListOf<FeedModel>()
    private var feedAdapter : FeedAdapter? = null
    lateinit var sharedPreferences: SharedPreferences
    private var page : Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentFeedBinding.inflate(inflater,container,false)
        sharedPreferences = requireContext().getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val titleName = sharedPreferences.getString("Title","The Avengers")
        binding?.txtName?.text = titleName
        apirequest()
        binding?.fabUploadImage?.setOnClickListener{
            val intentUpload = Intent(activity as Context, UploadActivity::class.java)
            startActivity(intentUpload)
        }
        binding?.nsvScrollFeed?.setOnScrollChangeListener(object :NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if (scrollY > oldScrollY) {
                    // Scrolling down
                    if (binding?.fabUploadImage?.isShown!!) {
                        binding?.fabUploadImage?.hide()
                    }
                } else {
                    // Scrolling up
                    if (!binding?.fabUploadImage?.isShown!!) {
                        binding?.fabUploadImage?.show()
                    }
                }
            }

        })
        binding?.latest?.setOnClickListener {
            page =1
            listfeed.clear()
            apirequest()
            //Toast.makeText(activity as Context ,"OOO",Toast.LENGTH_LONG).show()
        }
        binding?.older?.setOnClickListener {
            page =10
            listfeed.clear()
            apirequest()
        }

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.swipeRefreshLayout?.isRefreshing = true
            listfeed.clear()
            newapirequest()


        }

        swiptoGesture(binding?.rvFeedPost)
        return binding?.root
    }
    private fun apirequest(){

        if (ConnectionManager().checkConectivity(activity as Context)) {
            val queue = Volley.newRequestQueue(activity as Context)
            val url = "https://api.pexels.com/v1/curated/?page=${page}&per_page=10"
            val jsonObjectRequest =  object : JsonObjectRequest(
                Method.GET,url,null, Response.Listener
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
                            binding?.rvFeedPost?.layoutManager = LinearLayoutManager(activity as Context,
                                LinearLayoutManager.VERTICAL,false)
                            feedAdapter = FeedAdapter(activity as Context, listfeed)
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
        if (ConnectionManager().checkConectivity(activity as Context)) {
            val queue = Volley.newRequestQueue(activity as Context)
            val url = "https://api.pexels.com/v1/curated/?page=${page}&per_page=10"
            val jsonObjectRequest =  object : JsonObjectRequest(
                Method.GET,url,null, Response.Listener
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
                            binding?.rvFeedPost?.layoutManager = LinearLayoutManager(activity as Context,
                                LinearLayoutManager.VERTICAL,false)
                            feedAdapter = FeedAdapter(activity as Context, listfeed)
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
        AlertDialog.Builder(activity as Context).setMessage(
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
        val swipeGesture = object : SwipeShareCallBack(activity as Context){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                try{
                    when(direction){
                        ItemTouchHelper.RIGHT->{
                            val adapter = binding?.rvFeedPost?.adapter as FeedAdapter
                            val result = adapter.imagereturn(viewHolder.adapterPosition)

                            MediaScannerConnection.scanFile(
                                activity as Context, arrayOf(result), null
                            ) { path, uri ->
                                // This is used for sharing the image after it has being stored in the storage.
                                val shareIntent = Intent()
                                shareIntent.action = Intent.ACTION_SEND
                                shareIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    result
                                ) // A content: URI holding a stream of data associated with the Intent, used to supply the data being sent.
                                shareIntent.type =
                                    "text/plain"
                                // The MIME type of the data being handled by this intent.
                                startActivity(
                                    Intent.createChooser(
                                        shareIntent,
                                        "Share"
                                    )
                                )
                            }
                        }
                    }
                }catch (e : Exception){

                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(itemRV)

    }


}