package com.example.newshub.activity.splashscreenActivity.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.newshub.R
import com.example.newshub.activity.homescreenActivity.view.MainActivity
import com.example.newshub.activity.loginActivity.view.loginActivity
import com.example.newshub.databinding.ActivitySplashscreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class splashscreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser


        Handler().postDelayed({

            binding.lottieAnimation.playAnimation()
            checkUser(user)
            finish()
        }, 2500)
    }

    private fun checkUser(user: FirebaseUser?) {
        if (user != null) {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {

            var intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }
}