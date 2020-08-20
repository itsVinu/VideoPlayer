package com.example.allimages_from_mediastoreimage_table

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

//    var list: MutableList<Images>? = null
    var list = arrayListOf<Images>()
    val adapters = RVAdaptor(list)
    var granted = false

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        list = mutableListOf()
        p()

//        loadImages()
        loadVideos()
//        val adapters = RVAdaptor(this@MainActivity, list!!)
        rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@MainActivity.adapters
        }
        adapters.onItemClick = {
            val i = Intent(this,VideoPlayerActivity::class.java)
            i.putExtra("uri",it.uri.toString())
            i.putExtra("name",it.name.toString())

            startActivity(i)
        }


    }

    private fun loadVideos() {
        applicationContext.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE
            ),
            null,
            null,
            "${MediaStore.Video.Media.DISPLAY_NAME} ASC"
        ).use {
            var id = it!!.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            var name = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            var size = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            while (it.moveToNext()) {

                var idd = it.getLong(id)
                var names = it.getString(name)
                var sizes = it.getInt(size) / 1024

                var uri =
                    ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, idd)

                list!!.add(Images(uri, names, sizes))
            }
        }
//        rv.setHasFixedSize(true)
//        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        rv.adapter = RVAdaptor(this, list!!)
    }


    fun loadImages() {

        applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
            ),
            null,
            null,
            "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
        ).use {
            var id = it!!.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            var name = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            var size = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            while (it.moveToNext()) {

                var idd = it.getLong(id)
                var names = it.getString(name)
                var sizes = it.getInt(size) / 1024

                var uri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, idd)

                list!!.add(Images(uri, names, sizes))
            }
        }
//        rv.setHasFixedSize(true)
//        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        rv.adapter = RVAdaptor(this, list!!)
    }


    fun p() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            granted = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.size > 0) {
            if (grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
                granted = true
            } else {
                p()
            }
        }
    }
}
