package com.example.newshub.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("top-headlines")

    fun apiInterfaceGoogle(@Query("sources") sources : String, @Query("apiKey") apiKey : String):Call<GoogleModel>

    @GET("top-headlines")

    fun apiInterfaceNews(@Query("country") country : String, @Query ("category") category : String , @Query ("apiKey") apiKey:String ):Call<NewsModel>
}