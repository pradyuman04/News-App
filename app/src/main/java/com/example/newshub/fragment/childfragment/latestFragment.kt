package com.example.newshub.fragment.childfragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newshub.databinding.LatestFragmentBinding

import com.example.newshub.fragment.childfragment.childFrafmentAdapter.latestAdapter
import com.example.newshub.utils.Api.Companion.apiClass
import com.example.newshub.utils.Api.Companion.list
import com.example.newshub.utils.ApiClient
import com.example.newshub.utils.ApiInterface
import com.example.newshub.utils.ArticlesItem
import com.example.newshub.utils.GoogleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class latestFragment : Fragment() {

    lateinit var binding: LatestFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LatestFragmentBinding.inflate(layoutInflater)

        apiCallingGoogle()

        return binding.root
    }

    fun apiCallingGoogle() {


        var apiInterface = ApiClient.apiClientGoogle().create(ApiInterface::class.java)

        apiInterface.apiInterfaceGoogle("google-news-in", "8c1591602ef94434ab249e760ca1d995")
            .enqueue(object : Callback<GoogleModel> {
                override fun onResponse(call: Call<GoogleModel>, response: Response<GoogleModel>) {
                    Log.e("TAG", "onResponse: ${response.body()}")

                    var list = response.body()?.articles

                    binding.shimmerRela.isVisible = false

                    loaddata(list!!)
                }

                override fun onFailure(call: Call<GoogleModel>, error: Throwable) {
                    Log.e("TAG", "onFailure: ${error.message}")
                }
            })


    }

    fun loaddata(list: List<ArticlesItem>) {

        var adapter = latestAdapter(activity,list)
        var layoutManager = LinearLayoutManager(activity)
        binding.latestRecyclerView.adapter = adapter
        binding.latestRecyclerView.layoutManager = layoutManager

    }




}

