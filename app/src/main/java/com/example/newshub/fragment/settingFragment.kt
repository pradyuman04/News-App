package com.example.newshub.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.example.newshub.R
import com.example.newshub.databinding.SettingFragmentBinding

class settingFragment : Fragment() {

    lateinit var binding: SettingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SettingFragmentBinding.inflate(layoutInflater)

            binding.switchButton.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(button: CompoundButton?, ischecked: Boolean) {
                    if (ischecked){

                        nightMode()

                    }

                    else{

                        dayMode()

                    }

                }
            })

        return binding.root
    }

    fun dayMode(){

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


    }

    fun nightMode(){

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    }

}