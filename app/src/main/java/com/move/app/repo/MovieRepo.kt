package com.move.app.repo

import com.move.app.apihelper.ResponseHandler
import com.move.app.model.review.MovieReviewResponse

interface MovieRepo {
    suspend fun getMovieReview(): ResponseHandler<MovieReviewResponse>
}