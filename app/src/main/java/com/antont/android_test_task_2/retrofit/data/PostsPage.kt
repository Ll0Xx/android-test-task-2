package com.antont.android_test_task_2.retrofit.data

import com.google.gson.annotations.SerializedName

data class PostsPage(
    @SerializedName("total_pages")
    val totalPages: Int?,
    val page: Int?,
    val posts: List<Post>
)

