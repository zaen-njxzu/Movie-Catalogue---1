package com.zaen.moviecatalogue.ui.tvshow

import android.view.View
import com.zaen.moviecatalogue.adapters.BaseMovieAdapter
import kotlinx.android.synthetic.main.items_movie.view.*

class TvShowAdapter : BaseMovieAdapter() {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.apply {
            cv_ib_share.visibility = View.VISIBLE
        }
    }
}