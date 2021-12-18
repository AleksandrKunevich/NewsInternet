package com.example.newsinternet.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val API_KEY = "cf1cb57b7c4c43b7b112e5a912f04702"
    private const val BASE_URL = "https://newsapi.org/v2/"

    private var gson: Gson =GsonBuilder()
        .setLenient()
        .create()

    private fun getClient(url: String = BASE_URL) = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getNewsInternet(): NewsApi = getClient().create(NewsApi::class.java)
}