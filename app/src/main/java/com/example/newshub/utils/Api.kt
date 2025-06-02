package com.example.newshub.utils

import android.content.Intent
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Api {
    companion object {
        var apiClass = Api();
        var list = listOf<ArticlesItem>()
        var list2 = listOf<ArticlesItem2>()
    }

    fun apiCallingGoogle() {


        var apiInterface = ApiClient.apiClientGoogle().create(ApiInterface::class.java)
  
        apiInterface.apiInterfaceGoogle("google-news-uk", "92d90b5b65714afb87fb7e1d21950a6f")
            .enqueue(object : Callback<GoogleModel> {
                override fun onResponse(call: Call<GoogleModel>, response: Response<GoogleModel>) {
                    Log.e("TAG", "onResponse: ${response.body()}")

                    var temp = response.body()

//                    list = temp?.articles!!
                }

                override fun onFailure(call: Call<GoogleModel>, error: Throwable) {
                    Log.e("TAG", "onFailure: ${error.message}")
                }
            })


    }

    fun apiCallingNews(country: String, category: String): List<ArticlesItem2> {

        var apiInterface = ApiClient.apiClientNews().create(ApiInterface::class.java)
        apiInterface.apiInterfaceNews(country, category, "92d90b5b65714afb87fb7e1d21950a6f")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.e("TAG", "onResponse*********************: ${response.body()}")

                    var temp = response.body()

//                    list2 = temp?.articles!!

                }

                override fun onFailure(call: Call<NewsModel>, error: Throwable) {
                    Log.e("TAG", "onFailure: ${error.message}")
                }
            })
        return list2;
    }
}
