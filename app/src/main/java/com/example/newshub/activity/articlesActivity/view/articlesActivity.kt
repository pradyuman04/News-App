package com.example.newshub.activity.articlesActivity.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newshub.R
import com.example.newshub.activity.homescreenActivity.view.MainActivity
import com.example.newshub.databinding.ActivityArticlesBinding
import com.example.newshub.fragment.trendingFragment

class articlesActivity : AppCompatActivity() {

    lateinit var binding: ActivityArticlesBinding
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()

        click()

    }

    fun click(){

        binding.back.setOnClickListener {

           onBackPressed()

        }

    }

    fun setData(){

        var title = intent.getStringExtra("n1")
        var author = intent.getStringExtra("n2")
        var image = intent.getStringExtra("n3")
        var description = intent.getStringExtra("n4")
        var content = intent.getStringExtra("n5")


        binding.titleTxt.setText(title)
        binding.authorTxt.setText(author)
        Glide.with(this).load(image).into(binding.imageView)
        binding.descriptionTxt.setText(description)
        binding.contentTxt.setText(content)

    }

}
