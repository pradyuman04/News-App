package com.example.newshub.fragment.childfragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newshub.databinding.IndiaFragmentBinding
import com.example.newshub.fragment.childfragment.childFrafmentAdapter.IndiaAdapter
import com.example.newshub.fragment.childfragment.childFrafmentAdapter.WorldAdapter
import com.example.newshub.utils.*
import com.example.newshub.utils.Api.Companion.list
import com.example.newshub.utils.Api.Companion.list2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class indiaFragment : Fragment() {

    lateinit var binding: IndiaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = IndiaFragmentBinding.inflate(layoutInflater)

        apiCallingNews("in", "business")

        return binding.root
    }

    fun apiCallingNews(country: String, category: String): List<ArticlesItem2> {

        var apiInterface = ApiClient.apiClientNews().create(ApiInterface::class.java)
        apiInterface.apiInterfaceNews(country, category, "8c1591602ef94434ab249e760ca1d995")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.e("TAG", "onResponse*********************: ${response.body()}")

                    var list = response.body()?.articles

                    binding.shimmerRela.isVisible = false

                    loaddata(list!!)

                }

                override fun onFailure(call: Call<NewsModel>, error: Throwable) {
                    Log.e("TAG", "onFailure: ${error.message}")
                }
            })
        return Api.list2;
    }

    fun loaddata(list: List<ArticlesItem2>) {

        var adapter = IndiaAdapter(activity, list)
        var layoutManager = LinearLayoutManager(activity)
        binding.indiaRecyclerView.adapter = adapter
        binding.indiaRecyclerView.layoutManager = layoutManager

    }
}



