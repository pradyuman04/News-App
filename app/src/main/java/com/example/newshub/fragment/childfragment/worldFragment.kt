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
import com.example.newshub.activity.homescreenActivity.view.MainActivity
import com.example.newshub.databinding.WorldFragmentBinding
import com.example.newshub.fragment.childfragment.childFrafmentAdapter.WorldAdapter
import com.example.newshub.utils.*
import com.example.newshub.utils.Api.Companion.apiClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class worldFragment : Fragment() {

    lateinit var binding: WorldFragmentBinding
    var list2 = listOf<ArticlesItem2>()
    var l2 = listOf<ArticlesItem2>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WorldFragmentBinding.inflate(layoutInflater)

        apiCallingNews("au","business")

        return binding.root
    }


    fun apiCallingNews(country: String, category: String): List<ArticlesItem2> {

        var apiInterface = ApiClient.apiClientNews().create(ApiInterface::class.java)
        apiInterface.apiInterfaceNews(country, category, "8c1591602ef94434ab249e760ca1d995")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.e("TAG", "onResponse*********************: ${response.body()}")

                    /*var temp = response.body()

                    Api.list2 = temp?.articles!!*/

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

        var adapter = WorldAdapter(activity,list)
        var layoutManager = LinearLayoutManager(activity)
        binding.worldRecyclerView.adapter = adapter
        binding.worldRecyclerView.layoutManager = layoutManager

    }


}

/* override fun onStart() {
        super.onStart()
        l2 = apiClass.apiCallingNews("us","business")

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            loaddata(l2)
        }, 1000)
    }*/
    
