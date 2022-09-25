package com.example.newshub.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        var baseUrl = "https://newsapi.org/v2/"

        var baseUrl2 = "https://newsapi.org/v2/"

        fun apiClientGoogle(): Retrofit {

            var retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit

        }

        fun apiClientNews(): Retrofit {

            var retrofit = Retrofit.Builder().baseUrl(baseUrl2).addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit

        }
    }

}