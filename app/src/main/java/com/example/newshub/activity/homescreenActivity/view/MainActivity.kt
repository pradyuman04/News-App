package com.example.newshub.activity.homescreenActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newshub.R
import com.example.newshub.databinding.ActivityMainBinding
import com.example.newshub.fragment.homeFragment
import com.example.newshub.fragment.settingFragment
import com.example.newshub.fragment.trendingFragment
import com.example.newshub.utils.*
import com.example.newshub.utils.Api.Companion.apiClass
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onStart() {
        super.onStart()

        loadfragment(homeFragment())
        apiClass.apiCallingGoogle()
        apiClass.apiCallingNews("in", "business")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadfragment(homeFragment())

        binding.bottomBar.setOnItemSelectedListener { position ->
            if (position == 0) {

                loadfragment(homeFragment())
            } else if (position == 1) {

                loadfragment(trendingFragment())

            } else {
                loadfragment(settingFragment())
                //Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun loadfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }


}