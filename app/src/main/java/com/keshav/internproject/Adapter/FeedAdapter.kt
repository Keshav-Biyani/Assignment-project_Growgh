package com.keshav.internproject.Adapter

import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.keshav.internproject.Model.FeedModel
import com.keshav.internproject.R
import com.keshav.internproject.databinding.FeedImageBinding
import com.squareup.picasso.Picasso

class FeedAdapter(val context: Context,private val listof : ArrayList<FeedModel>):
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
     class ViewHolder(binding: FeedImageBinding):RecyclerView.ViewHolder(binding.root) {
        var iv_image = binding.feedPostImage
         var iv_like = binding.ivLike
         var ivShare = binding.ivShare
         var txtShare = binding.txtShare
         var txtLike = binding.txtLike
         var txtCommetn = binding.txtComm
         var iv_comm = binding.ivComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
      return ViewHolder(FeedImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        val feed = listof[position]
        holder.ivShare.setOnClickListener{
            share(context,feed.url)
        }
        holder.txtShare.setOnClickListener {
            share(context,feed.url)
        }
        holder.txtCommetn.setOnClickListener {
            Toast.makeText(context,"Comment",Toast.LENGTH_SHORT).show()
        }
        holder.iv_comm.setOnClickListener {
            Toast.makeText(context,"Comment",Toast.LENGTH_SHORT).show()
        }
        var isLiked = false
        holder.txtLike.setOnClickListener {
            isLiked = Like(isLiked,holder)

        }
        holder.iv_like.setOnClickListener {
 isLiked = Like(isLiked,holder)

        }
        Picasso.get().load(feed.url).error(R.drawable.bro).into(holder.iv_image)
    }
    fun imagereturn(position: Int): String  {
return listof[position].url
    }
   private fun share(context: Context,result : String){
        MediaScannerConnection.scanFile(
            context, arrayOf(result), null
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
           context.startActivity(Intent.createChooser(shareIntent, "Share"))
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    private fun Like(isLiked : Boolean, holder : FeedAdapter.ViewHolder) : Boolean{
        val zoomInAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        val zoomOutAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
        if(isLiked){
            holder.iv_like.setImageResource(R.drawable.baseline_favorite_border_24)
            holder.iv_like.startAnimation(zoomInAnim)
            holder.iv_like.startAnimation(zoomOutAnim)
            val t = false
            holder.txtLike.setText("21 Like")
            return t
            // share(context,feed.url)
        } else{
            holder.iv_like.setImageResource(R.drawable.baseline_favorite_24_red)
            holder.iv_like.startAnimation(zoomInAnim)
            holder.iv_like.startAnimation(zoomOutAnim)
            val i= true
            holder.txtLike.setText("22 Like")
            return i


        }
    }
    override fun getItemCount(): Int {
        return listof.size
    }
    interface OnClickListener {
        fun onClick(position: Int, model: FeedModel)
    }

}