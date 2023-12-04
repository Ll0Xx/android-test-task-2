package com.antont.android_test_task_2

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    private const val BASE_URL = "http://citymani.ezrdv.org/"
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService: PostsApi by lazy {
        retrofit.create(PostsApi::class.java)
    }
}