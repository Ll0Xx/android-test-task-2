package com.antont.android_test_task_2.retrofit

import com.antont.android_test_task_2.retrofit.data.PostsPage
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("main/test")
    suspend fun getPostPage(@Query("page") pageNum:Int): PostsPage
}