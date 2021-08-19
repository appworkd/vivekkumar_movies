package com.move.app.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.move.app.apihelper.ResponseHandler
import com.move.app.getOrAwaitValueTest
import com.move.app.model.review.MovieReviewResponse
import com.move.app.repo.FakeMovieRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MovieReviewVMTest {
    private lateinit var viewModel: MovieReviewVM

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieReviewVM(FakeMovieRepository())
    }

    @Test
    fun `if request successful return response`() {
        val movieReviews = MovieReviewResponse()
        val result = viewModel.movieReviewResponse.getOrAwaitValueTest()
        assertThat(result.data).isEqualTo(ResponseHandler.Success(movieReviews))
    }

    @Test
    fun `if request unsuccessful return error`() {
        val result = viewModel.movieReviewResponse.getOrAwaitValueTest()
        assertThat(result.data).isEqualTo(ResponseHandler.Error("Something went wrong", null))
    }


}