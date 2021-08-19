package com.move.app.model.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieReviewResponse(
    @Json(name = "copyright")
    val copyright: String="",
    @Json(name = "has_more")
    val hasMore: Boolean=true,
    @Json(name = "num_results")
    val numResults: Int=0,
    @Json(name = "results")
    val results: List<Result> = arrayListOf(),
    @Json(name = "status")
    val status: String=""
)