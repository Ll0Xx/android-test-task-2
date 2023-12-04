package com.antont.android_test_task_2.data

import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import java.time.LocalDate

data class Post(
    val id: BigInteger,
    val userName: String,
    val userId: String,
    val userPic: String?,
    val message: String?,
    val photo: String?,
    @SerializedName("date")
    val stringDate: String,
    var parsedDate: LocalDate?
) {
    init {
        parsedDate = LocalDate.parse(stringDate)
    }
}
