package com.example.beuon.roleMenu.ui.courses

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import com.example.beuon.R
import com.example.beuon.SignupActivity
import java.util.concurrent.TimeUnit


class CourseFragment : Fragment() {

    private lateinit var dashboardViewModel: CourseViewModel

    var srcPath = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
    lateinit var root : View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState);
        dashboardViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CourseViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_courses, container, false)
        val textView: TextView = root.findViewById(R.id.text_username)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "tutortest"
        })

        startVideo()

        val createBtn : Button = root.findViewById(R.id.create_class_btn)
        createBtn.setOnClickListener {
            val intent = Intent(activity, SignupActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    fun startVideo(){
        val videoView = root.findViewById(R.id.course_video) as VideoView
        val mediaController = MediaController(activity)
        mediaController.setAnchorView(videoView)
        val uri = Uri.parse("android.resource://" + getActivity()!!.getPackageName() + "/" + R.raw.html)
        videoView.seekTo(10000)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.pause()

        videoView.setOnPreparedListener {
            val duration = videoView.duration.toLong()
            val timeInHour = String.format("%02d:%02d:%02d Hours.",
                    TimeUnit.MILLISECONDS.toHours(duration),
                    TimeUnit.MILLISECONDS.toMinutes(duration) -
                            TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                    TimeUnit.MILLISECONDS.toSeconds(duration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
            );
            val textTime : TextView = root.findViewById(R.id.text_time)
            textTime.text = timeInHour
            val textTitle : TextView = root.findViewById(R.id.text_title)
            textTitle.text = "HTML For Beginner"
            val textDesc : TextView = root.findViewById(R.id.text_desc)
            textDesc.text = "Html Tutorial for beginner can study."
            val textPrice : TextView = root.findViewById(R.id.text_price)
            textPrice.text = "250 / Hrs."
        }
    }

}