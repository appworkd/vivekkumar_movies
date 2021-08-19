package com.move.app.apihelper

import com.move.app.model.review.MovieReviewResponse
import com.move.app.utils.Constants.END_POINT_REVIEW
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(END_POINT_REVIEW)
    suspend fun getMovieReviewList(
        @Path("type") type:String="all",
        @Query("offset") offset: Int = 1,
        @Query("order") order: String = "by-opening-date",
        @Query("api-key") apiKey: String="9V9583EMDQWOfC5P35GxuT2kdvka0OTm"
    ): Response<MovieReviewResponse>
}