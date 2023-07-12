package com.keshav.internproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keshav.internproject.Model.FeedModel
import com.keshav.internproject.R
import com.keshav.internproject.databinding.FeedImageBinding
import com.squareup.picasso.Picasso

class FeedAdapter(val context: Context,private val listof : ArrayList<FeedModel>):
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
     class ViewHolder(binding: FeedImageBinding):RecyclerView.ViewHolder(binding.root) {
        var iv_image = binding.feedPostImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
      return ViewHolder(FeedImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        val feed = listof[position]

        Picasso.get().load(feed.url).error(R.drawable.bro).into(holder.iv_image)
    }

    override fun getItemCount(): Int {
        return listof.size
    }

}