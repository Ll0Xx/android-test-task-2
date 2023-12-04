package com.antont.android_test_task_2.data

data class PostsPage(
    private val totalPages: Int,
    private val page: Int,
    private val posts: List<Post>
)

