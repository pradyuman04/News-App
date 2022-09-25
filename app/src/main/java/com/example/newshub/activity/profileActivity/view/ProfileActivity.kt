package com.example.newshub.activity.profileActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newshub.R
import com.example.newshub.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class profileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserInfo()

        backtoHome()

    }

    fun setUserInfo(){

        var firebaseAuth = FirebaseAuth.getInstance()

        var user = firebaseAuth.currentUser

        Glide.with(this)
            .load(user?.photoUrl)
            .into(binding.profileImage);

        binding.userNameTxt.setText(user?.displayName)

        binding.userEmailTxt.setText(user?.email)

        binding.userNumberTxt.setText(user?.phoneNumber)


    }

    fun backtoHome(){

        binding.back.setOnClickListener {

            onBackPressed()

        }

    }
}