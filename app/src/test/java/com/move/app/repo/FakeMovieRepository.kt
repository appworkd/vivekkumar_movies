package com.move.app.repo

import com.move.app.apihelper.ResponseHandler
import com.move.app.model.review.MovieReviewResponse

class FakeMovieRepository : MovieRepo {
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getMovieReview(): ResponseHandler<MovieReviewResponse> {
        return if(shouldReturnNetworkError){
            ResponseHandler.Error("NoNetwork",null)
        }else{
            ResponseHandler.Success(MovieReviewResponse())
        }
    }
}