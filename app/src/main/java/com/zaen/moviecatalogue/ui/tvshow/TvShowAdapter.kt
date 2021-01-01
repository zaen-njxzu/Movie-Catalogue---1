package com.zaen.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zaen.moviecatalogue.adapters.BaseMovieAdapter
import com.zaen.moviecatalogue.databinding.ItemsMovieBinding
import com.zaen.moviecatalogue.models.Movie

class TvShowAdapter : BaseMovieAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsBaseMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsBaseMovieBinding)
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding): BaseMovieAdapter.MovieViewHolder(binding) {
        override fun bind(movie: Movie) {
            super.bind(movie)
            binding.apply {
                cvIbShare.visibility = View.VISIBLE
                ibShare.setOnClickListener {
                    onClickShareListener?.let {
                        it(movie)
                    }
                }
            }
        }
    }

    private var onClickShareListener: ((Movie) -> Unit)? = null

    fun setOnClickShareListener(listener: (Movie) -> Unit) {
        onClickShareListener = listener
    }
}