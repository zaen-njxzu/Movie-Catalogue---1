package com.zaen.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.FragmentTvShowBinding
import com.zaen.moviecatalogue.pager.TypeMoviePagerAdapter
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.utils.TypeViewSource
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory
import com.zaen.moviecatalogue.vo.Status

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            tvShowAdapter = TvShowAdapter()
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

            fragmentTvShowBinding.rvTvShow.setHasFixedSize(true)
            fragmentTvShowBinding.rvTvShow.adapter = tvShowAdapter

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            arguments?.takeIf { it.containsKey(TypeMoviePagerAdapter.TYPE_VIEW_ARG) }?.apply {
                val typeView = getInt(TypeMoviePagerAdapter.TYPE_VIEW_ARG)

                when(typeView) {
                    TypeViewSource.FAVORITE.ordinal -> observeTvShowFavorite()
                    TypeViewSource.CATALOGUE.ordinal -> observeTvShowCatalogue()
                }
            }


        }
    }

    fun observeTvShowFavorite() {
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            tvShowAdapter.submitList(tvShows)
        })
    }

    fun observeTvShowCatalogue() {
        viewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if(tvShows != null) {
                when(tvShows.status) {
                    Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
                        tvShowAdapter.submitList(tvShows.data)
                    }
                    Status.ERROR -> {
                        fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }

}