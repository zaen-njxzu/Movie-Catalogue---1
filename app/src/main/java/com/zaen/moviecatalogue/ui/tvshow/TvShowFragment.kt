package com.zaen.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.utils.TypeMovie
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.differ.submitList(tvShows)
            tvShowAdapter.setOnItemClickListener {
                context?.apply {
                    val intent = Intent(this, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
                    intent.putExtra(DetailMovieActivity.EXTRA_TYPE_MOVIE, TypeMovie.TV_SHOW.ordinal)
                    startActivity(intent)
                }
            }
            tvShowAdapter.setOnClickShareListener { movie ->
                if(activity != null) {
                    val mimeType = "text/plain"
                    ShareCompat.IntentBuilder
                        .from(activity!!)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan aplikasi ini sekarang.")
                        .setText("Segera lihat film ${movie.title} di Bioskop terdekat")
                        .startChooser()
                }
            }

            rv_tv_show.setHasFixedSize(true)
            rv_tv_show.adapter = tvShowAdapter
        }
    }
}