package com.move.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.move.app.apihelper.ResponseHandler
import com.move.app.model.review.MovieReviewResponse
import com.move.app.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieReviewVM @Inject constructor(
    private val mainMovieRepo: MovieRepo
) : ViewModel() {
    private var _movieReviewResponse =
        MutableLiveData<ResponseHandler<MovieReviewResponse>>()
    val movieReviewResponse: LiveData<ResponseHandler<MovieReviewResponse>>
        get() = _movieReviewResponse

    suspend fun getMovieResult() {
        viewModelScope.launch {

            when (val result = mainMovieRepo.getMovieReview()) {
                is ResponseHandler.Authentication -> {
                    _movieReviewResponse.postValue(
                        ResponseHandler.Authentication(
                            result.message,
                            null
                        )
                    )
                }
                is ResponseHandler.Error -> {
                    _movieReviewResponse.postValue(
                        ResponseHandler.Error(
                            result.message,
                            null
                        )
                    )
                }
                is ResponseHandler.Loading -> TODO()
                is ResponseHandler.Nothing -> TODO()
                is ResponseHandler.Success -> {
                    _movieReviewResponse.postValue(ResponseHandler.Success(result.data))
                }
            }
        }
    }
}