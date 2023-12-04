package com.antont.android_test_task_2.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.math.BigInteger
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class Post(
    val id: BigInteger,
    val userName: String,
    val userId: String,
    val userPic: String?,
    val message: String?,
    val photo: String?,
    val date: LocalDate?
)

internal class DateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate? {
        json?.let {
            val formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy")
            return LocalDate.parse(json.asString, formatter)
        }
        return null
    }
}