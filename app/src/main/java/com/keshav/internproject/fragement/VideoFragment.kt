package com.keshav.internproject.fragement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keshav.internproject.Adapter.VedioAdapter
import com.keshav.internproject.Model.VedioModel
import com.keshav.internproject.R
import com.keshav.internproject.databinding.FragmentFeedBinding
import com.keshav.internproject.databinding.FragmentVedioBinding
import com.keshav.internproject.databinding.VedioBinding


class VideoFragment : Fragment() {
private var binding : FragmentVedioBinding? = null
    private var listVideo = arrayListOf<VedioModel>()
    private var vedioAdapter : VedioAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVedioBinding.inflate(inflater,container,false)

val vedio1 = VedioModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        val vedio2 = VedioModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
        val vedio3 = VedioModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4")
        listVideo.add(vedio1)
        listVideo.add(vedio2)
        listVideo.add(vedio3)
        vedioAdapter = VedioAdapter(activity as Context,listVideo)
        binding?.viewPager?.adapter = vedioAdapter
        return binding?.root
    }

}