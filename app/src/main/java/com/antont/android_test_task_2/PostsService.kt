package com.antont.android_test_task_2

import com.antont.android_test_task_2.data.PostsPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {
    @GET("main/test")
    fun getPostPage(@Query("page") pageNum:Int): Call<PostsPage>
}