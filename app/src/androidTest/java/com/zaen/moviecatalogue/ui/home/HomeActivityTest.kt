package com.zaen.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import com.zaen.moviecatalogue.utils.RecyclerViewMatcher
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummyMovie.getMovies()
    private val dummyDetailMovies = DataDummyMovie.getMoviesDetail()
    private val dummyTvShows = DataDummyTvShow.getTvShows()
    private val dummyDetailTvShows = DataDummyTvShow.getTvShowsDetail()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyDetailMovies[0].title)))
        onView(withId(R.id.tv_release_at)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_at)).check(matches(withText(dummyDetailMovies[0].releaseDate)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyDetailMovies[0].rating.toString())))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyDetailMovies[0].overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyDetailTvShows[0].title)))
        onView(withId(R.id.tv_release_at)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_at)).check(matches(withText(dummyDetailTvShows[0].releaseDate)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyDetailTvShows[0].rating.toString())))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyDetailTvShows[0].overview)))
    }

    @Test
    fun shareTvShow() {
        onView(withText(R.string.tvshow)).perform(click())
        onView(RecyclerViewMatcher(R.id.rv_tv_show).atPositionOnView(0, R.id.cv_ib_share)).check(
            matches(isDisplayed()))
        onView(RecyclerViewMatcher(R.id.rv_tv_show).atPositionOnView(0, R.id.ib_share)).check(
            matches(isDisplayed()))
        onView(RecyclerViewMatcher(R.id.rv_tv_show).atPositionOnView(0, R.id.ib_share)).perform(
            click())
    }

}