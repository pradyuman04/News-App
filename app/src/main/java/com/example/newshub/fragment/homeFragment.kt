package com.example.newshub.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newshub.R
import com.example.newshub.activity.profileActivity.view.profileActivity
import com.example.newshub.controller.tabLayotAdapter
import com.example.newshub.databinding.HomeFragmentBinding
import com.example.newshub.fragment.childfragment.indiaFragment
import com.example.newshub.fragment.childfragment.latestFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class homeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {

        binding = HomeFragmentBinding.inflate(layoutInflater)

        setTabLayout()

        setProfileImage()

        viewProfile()

        return binding.root
    }

    fun viewProfile(){

        binding.profileImage.setOnClickListener {

            var intent = Intent(activity,profileActivity::class.java)
            activity?.startActivity(intent)

        }

    }

    private fun setProfileImage() {
        var firebaseAuth = FirebaseAuth.getInstance()
        var userImage = firebaseAuth.currentUser?.photoUrl

        Glide.with(this)
            .load(userImage)
            .into(binding.profileImage);
    }

    private fun setTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Latest"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("India"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("World"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Business"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Technology"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Entertainment"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sport"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Science"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Health"))

        var adapter = tabLayotAdapter(activity, childFragmentManager, 5)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })
    }

}