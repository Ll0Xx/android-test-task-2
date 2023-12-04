package com.antont.android_test_task_2.data

import java.util.Date

data class Post(
    private val id: Int,
    private val userName: String,
    private val userPic: String,
    private val message: String,
    private val photo:String,
    private val date:Date
)
