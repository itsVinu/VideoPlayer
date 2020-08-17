package com.example.allimages_from_mediastoreimage_table

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VideoPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val uri = intent.getStringExtra("uri")
        val name = intent.getStringExtra("name")
    }
}
