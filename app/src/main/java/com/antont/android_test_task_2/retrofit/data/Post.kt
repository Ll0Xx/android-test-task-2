package com.antont.android_test_task_2.retrofit.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


data class Post(
    val id: BigInteger,
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("user_pic") val userPic: String?,
    val message: String?,
    val photo: String?,
    val date: LocalDateTime?
)

internal class DateDeserializer : JsonDeserializer<LocalDateTime> {
    override fun deserialize(
        json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?
    ): LocalDateTime? {
        json?.let {
            val parsedDate: LocalDateTime
            try {
                val formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)
                parsedDate = LocalDateTime.parse(json.asString, formatter)
            } catch (e: Exception) {
                return null
            }
            return parsedDate
        }
        return null
    }
}