package com.example.basics.demo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.basics.R
import com.example.basics.countryModule.view.CountryListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

private const val IMAGE_URL =
    "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"
private val coroutineScope = CoroutineScope(Dispatchers.Main)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineScope.launch {
            val deferred = coroutineScope.async(Dispatchers.IO) { loadBitmap() }
            val bitmap = deferred.await()
//            val updateDeferred = coroutineScope.async(Dispatchers.Unconfined){Filter.apply(bitmap)}
//            val updateBitmap = updateDeferred.await()
//            setBitmap(updateBitmap)
            setBitmap(bitmap)
        }
        listener()
    }

    private fun listener() {
        btn_basic_next.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CountryListActivity::class.java
                )
            )
        }
    }

    private suspend fun setBitmap(b: Bitmap) {
        progressBar.visibility = View.GONE
        img_dog_pic.visibility = View.VISIBLE
        img_dog_pic.setImageBitmap(b)
    }

    private suspend fun loadBitmap(): Bitmap = URL(IMAGE_URL).openStream().use {
        BitmapFactory.decodeStream(it)
    }


}