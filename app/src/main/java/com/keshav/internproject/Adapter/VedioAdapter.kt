package com.keshav.internproject.Adapter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaScannerConnection
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keshav.internproject.Model.FeedModel
import com.keshav.internproject.Model.VedioModel
import com.keshav.internproject.R
import com.keshav.internproject.databinding.FeedImageBinding
import com.keshav.internproject.databinding.VedioBinding

class VedioAdapter(val context: Context, private val listof : ArrayList<VedioModel>):
RecyclerView.Adapter<VedioAdapter.ViewHolder>() {
    class ViewHolder(binding: VedioBinding):RecyclerView.ViewHolder(binding.root) {
val vvLike = binding.vvlike
        val ivShare = binding.ivShareVedio
        val likeVedio = binding.likevedio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return VedioAdapter.ViewHolder(
            VedioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listof.size
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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vedio = listof[position]
        holder.vvLike.setVideoURI(Uri.parse(vedio.url))
        holder.vvLike.setOnPreparedListener( object  :MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer): Unit{
                mp.start()
                val vidRatio : Float = (mp.videoWidth/mp.videoHeight).toFloat()
                val screenRatio : Float = (holder.vvLike.width/holder.vvLike.height).toFloat()
                val scale  = vidRatio/screenRatio
//                if(scale>=1){
//                    holder.vvLike.scaleX = scale
//                }else{
//                    holder.vvLike.scaleY = 1f/scale
//                }
            }

        })
        holder.vvLike.setOnCompletionListener (object :MediaPlayer.OnCompletionListener{
            override fun onCompletion(mp: MediaPlayer?) {
                mp?.start()
            }

        } )
        holder.ivShare.setOnClickListener {
            share(context,vedio.url)
        }
        var isLiked = false
        holder.likeVedio.setOnClickListener {
            isLiked = Like(isLiked,holder)
        }


        }
    private fun Like(isLiked : Boolean, holder : VedioAdapter.ViewHolder) : Boolean{
        if(isLiked){
            holder.likeVedio.setImageResource(R.drawable.baseline_favorite_border_24)
            val t = false

            return t
            // share(context,feed.url)
        } else{
            holder.likeVedio.setImageResource(R.drawable.baseline_favorite_24_red)
            val i= true

            return i


        }
    }    }
