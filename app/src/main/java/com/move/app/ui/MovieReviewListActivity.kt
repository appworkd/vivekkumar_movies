package com.move.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.move.app.apihelper.ApiInterface
import com.move.app.apihelper.ResponseHandler
import com.move.app.databinding.ActivityMvoieReviewListBinding
import com.move.app.model.review.Result
import com.move.app.utils.ConnectionLiveData
import com.move.app.utils.Constants.KEY_MOVIE
import com.move.app.utils.EspressoIdlingResource
import com.move.app.utils.showSnackBar
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieReviewListActivity : AppCompatActivity(), MovieReviewAdapter.Interaction {
    private val vbReviewList by lazy {
        ActivityMvoieReviewListBinding.inflate(layoutInflater)
    }
    private val movieReviewAdapter by lazy {
        MovieReviewAdapter(this)
    }
    private val connection by lazy {
        ConnectionLiveData(this)
    }
    private val vm: MovieReviewVM by viewModels()

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var moshi: Moshi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vbReviewList.root)
        setUpRecyclerView()
        connection.observe(this) {
            if (it) {
                lifecycleScope.launch() {
                    vm.getMovieResult()
                }
            } else {
                vbReviewList.root.showSnackBar(
                    "No Internet"
                )
            }
        }

        vm.movieReviewResponse.observe(this) { response ->
            when (response) {
                is ResponseHandler.Authentication -> {
                    val msg = response.message?.let { it } ?: "Something went wrong"
                    vbReviewList.root.showSnackBar(
                        msg
                    )
                }
                is ResponseHandler.Error -> {
                    val msg = response.message?.let { it } ?: "Something went wrong"
                    vbReviewList.root.showSnackBar(
                        msg
                    )
                }
                is ResponseHandler.Loading -> TODO()
                is ResponseHandler.Nothing -> TODO()
                is ResponseHandler.Success -> {
                    EspressoIdlingResource.increament()
                    movieReviewAdapter.submitList(
                        response.data!!.results
                    )
                    EspressoIdlingResource.decrement()
                }
            }
        }

    }


    private fun setUpRecyclerView() {
        vbReviewList.rvMovie.apply {
            adapter = movieReviewAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
    }

    override fun onItemSelected(position: Int, item: Result) {
        startActivity(
            Intent(this, MovieDetailsActivity::class.java)
                .putExtra(KEY_MOVIE, item)
        )

    }
}