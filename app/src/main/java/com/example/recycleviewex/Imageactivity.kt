package com.example.recycleviewex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class Imageactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageactivity)

        val images:ImageView=findViewById(R.id.imageView)

       intent.extras

        Picasso.get().load(intent.getStringExtra("Image")).into(images)

    }

}
