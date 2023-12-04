package com.antont.android_test_task_2

import com.antont.android_test_task_2.data.DateDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate


object RetrofitHelper {
    private const val BASE_URL = "http://citymani.ezrdv.org/"
    private val gson =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, DateDeserializer()).create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService: PostsApi by lazy {
        retrofit.create(PostsApi::class.java)
    }
}