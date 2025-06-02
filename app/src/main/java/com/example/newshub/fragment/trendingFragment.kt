package com.example.newshub.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newshub.databinding.TrendingFragmentBinding
import com.example.newshub.fragment.fragmentAdapter.trendingAdapter
import com.example.newshub.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class trendingFragment : Fragment() {

    lateinit var binding: TrendingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrendingFragmentBinding.inflate(layoutInflater)

        apiCallingNews("au","business")

        return binding.root
    }

    fun apiCallingNews(country: String, category: String): List<ArticlesItem2> {

        var apiInterface = ApiClient.apiClientNews().create(ApiInterface::class.java)
        apiInterface.apiInterfaceNews(country, category, "8c1591602ef94434ab249e760ca1d995")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.e("TAG", "onResponse*********************: ${response.body()}")

                    var list = response.body()?.articles


                    setupRv(list!!)

                }

                override fun onFailure(call: Call<NewsModel>, error: Throwable) {
                    Log.e("TAG", "onFailure: ${error.message}")
                }
            })
        return Api.list2;
    }

    fun setupRv(list: List<ArticlesItem2>) {

        var trendingAdapter = trendingAdapter(activity, list)
        var layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = trendingAdapter
        binding.recyclerView.layoutManager = layoutManager

    }

}
