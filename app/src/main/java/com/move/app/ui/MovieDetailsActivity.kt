package com.move.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.move.app.R
import com.move.app.databinding.ActivityMovieDetailsBinding
import com.move.app.model.review.Result
import com.move.app.utils.Constants.KEY_MOVIE
import com.move.app.utils.setUpImage

class MovieDetailsActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MovieDetailsActivity"
    }

    private val vbDetails by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }
    private var currentMovieItem: Result? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vbDetails.root)
        intent?.let {
            if (it.hasExtra(KEY_MOVIE)) {
                currentMovieItem = it.getSerializableExtra(KEY_MOVIE) as Result
                currentMovieItem?.let { movieItem ->
                    setUpData(movieItem)
                }
            }
        }
    }

    private fun setUpData(movieItem: Result) {
        vbDetails.let {
            this.setUpImage(
                movieItem.multimedia!!.src,
                it.imageViewMovieHeader
            )
            this.setUpImage(
                movieItem.multimedia!!.src,
                it.imageViewMovieSubHeader
            )
            it.movieName.text = movieItem.displayTitle
            it.movieBy.text = getString(R.string.by, movieItem.byline)
            it.movieDate.text = movieItem.openingDate
            it.movieHeadline.text = movieItem.headline
            it.movieSummary.text = movieItem.summaryShort
        }
    }
}