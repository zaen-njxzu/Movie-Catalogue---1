package com.zaen.moviecatalogue.pager

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.ui.movie.MovieFragment
import com.zaen.moviecatalogue.ui.tvshow.TvShowFragment
import com.zaen.moviecatalogue.utils.TypeViewSource

class TypeMoviePagerAdapter(fm: Fragment, private val typeViewSource: TypeViewSource) : FragmentStateAdapter(fm) {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvshow)
        val TYPE_VIEW_ARG = "type_view_arg"
    }

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()

        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }

        fragment.arguments = Bundle().apply {
            putInt(TYPE_VIEW_ARG, typeViewSource.ordinal)
        }

        return fragment
    }

}
