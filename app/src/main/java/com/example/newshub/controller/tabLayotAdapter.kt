package com.example.newshub.controller

import android.text.TextUtils.replace
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newshub.fragment.childfragment.*

class tabLayotAdapter(val activity: FragmentActivity?,val childFragmentManager: FragmentManager,val i: Int) :

    FragmentPagerAdapter(childFragmentManager) {
        override fun getCount(): Int {
            return 9
        }
        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> latestFragment()
                1 -> indiaFragment()
                2 -> worldFragment()
                3 -> businessFragment()
                4 -> technologyFragment()
                5 -> entertainmentFragment()
                6 -> sportFragment()
                7 -> scienceFragment()
                8 -> healthFragment()

                else -> latestFragment()

            }
        }
}