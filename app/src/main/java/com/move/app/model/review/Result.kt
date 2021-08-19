package com.move.app.model.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "byline")
    val byline: String = "",
    @Json(name = "critics_pick")
    val criticsPick: Int = 0,
    @Json(name = "date_updated")
    val dateUpdated: String = "",
    @Json(name = "display_title")
    val displayTitle: String = "",
    @Json(name = "headline")
    val headline: String = "",
    @Json(name = "link")
    val link: Link? = null,
    @Json(name = "mpaa_rating")
    val mpaaRating: String = "",
    @Json(name = "multimedia")
    val multimedia: Multimedia? = null,
    @Json(name = "opening_date")
    val openingDate: String = "",
    @Json(name = "publication_date")
    val publicationDate: String = "",
    @Json(name = "summary_short")
    val summaryShort: String = ""
) : Serializable