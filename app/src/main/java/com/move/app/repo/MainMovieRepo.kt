package com.move.app.repo

import com.move.app.apihelper.ApiInterface
import com.move.app.apihelper.ResponseHandler
import com.move.app.model.review.MovieReviewResponse
import javax.inject.Inject

class MainMovieRepo @Inject constructor(
    private val apiInterface: ApiInterface
) : MovieRepo {

    override suspend fun getMovieReview(): ResponseHandler<MovieReviewResponse> {
        return try {
            val response = apiInterface.getMovieReviewList()
            when (response.code()) {
                200 -> {
                    ResponseHandler.Success(
                        response.body()
                    )
                }
                401 -> {
                    ResponseHandler.Authentication(
                        "Unauthorized request",
                        null
                    )
                }
                500 -> {
                    ResponseHandler.Authentication(
                        "Internal server error",
                        null
                    )
                }
                else -> ResponseHandler.Authentication(
                    "Something went wrong",
                    null
                )
            }
        } catch (e: Exception) {
            ResponseHandler.Error(e.message, null)
        }
    }
}