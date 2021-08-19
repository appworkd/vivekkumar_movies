package com.move.app.model.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Multimedia(
    @Json(name = "height")
    val height: Int,
    @Json(name = "src")
    val src: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "width")
    val width: Int
):Serializable