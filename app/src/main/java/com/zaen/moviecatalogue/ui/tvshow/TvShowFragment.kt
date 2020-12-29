package com.zaen.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.differ.submitList(tvShows)

            rv_tv_show.setHasFixedSize(true)
            rv_tv_show.adapter = tvShowAdapter
        }
    }
}