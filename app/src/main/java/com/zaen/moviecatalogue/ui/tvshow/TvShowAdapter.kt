package com.zaen.moviecatalogue.ui.tvshow

import android.view.View
import com.zaen.moviecatalogue.adapters.BaseMovieAdapter
import com.zaen.moviecatalogue.models.Movie
import kotlinx.android.synthetic.main.items_movie.view.*

class TvShowAdapter : BaseMovieAdapter() {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val movie = differ.currentList[position]
        holder.itemView.apply {
            cv_ib_share.visibility = View.VISIBLE
            ib_share.setOnClickListener {
                onClickShareListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onClickShareListener: ((Movie) -> Unit)? = null

    fun setOnClickShareListener(listener: (Movie) -> Unit) {
        onClickShareListener = listener
    }
}