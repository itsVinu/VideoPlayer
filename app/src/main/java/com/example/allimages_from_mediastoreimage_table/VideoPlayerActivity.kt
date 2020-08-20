package com.example.allimages_from_mediastoreimage_table

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer : SimpleExoPlayer
    private lateinit var dataSourceFactory: DataSource.Factory
    lateinit var uri:String
    lateinit var name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        uri = intent.getStringExtra("uri")
        name = intent.getStringExtra("name")
        playerView = findViewById(R.id.player_view)

//        if (Util.SDK_INT > 23)
//            initialisePlayer()
    }

    private fun initialisePlayer() {
        playerView = findViewById(R.id.player_view)
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)


        dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this,"MyExoPlayer"))

        val mediasource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(
            uri))

        simpleExoPlayer.prepare(mediasource, false, false)
        simpleExoPlayer.playWhenReady = true
        playerView.player = simpleExoPlayer
    }

    private fun releasePlayer() {
        simpleExoPlayer.release()
    }


    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23)
            initialisePlayer()
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 23)
            initialisePlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) releasePlayer()
    }


    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) releasePlayer()
    }


    companion object {
//        const val STREAM_URL = "https://storage.googleapis.com/exoplayer-test-media-0/firebase-animation.mp4"
        const val MP4_URI =  "https://storage.googleapis.com/exoplayer-test-media-0/firebase-animation.mp4"
//        http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
    }
}

