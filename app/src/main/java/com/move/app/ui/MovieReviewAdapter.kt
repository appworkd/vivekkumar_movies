package com.move.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.move.app.databinding.IvMovieBinding
import com.move.app.model.review.Result
import com.move.app.utils.EspressoIdlingResource
import com.move.app.utils.setUpImage

class MovieReviewAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.displayTitle == newItem.displayTitle
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewBinding = IvMovieBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
        return MovieReviewVH(
            itemViewBinding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieReviewVH -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Result>) {
        EspressoIdlingResource.increament()
        val dataCommitCallback = Runnable {
            EspressoIdlingResource.decrement()
        }
        differ.submitList(list,dataCommitCallback)
    }

    inner class MovieReviewVH
    constructor(
        private val itemVb: IvMovieBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemVb.root) {

        fun bind(movieItem: Result) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, movieItem)
            }
            movieItem?.let { movie ->
                itemVb.tvMovieName.text = movie.displayTitle
                itemView.context.setUpImage(
                    movie.multimedia!!.src,
                    itemVb.ivMovie
                )
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Result)
    }
}