package com.move.app.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.move.app.R
import com.move.app.model.review.Result
import com.move.app.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieReviewListActivityTest {
    private val movieList = mutableListOf<Result>()
    private val POS_MOVIE = 1

    @Before
    fun setUp() {
        var i = 0
        repeat(20) {
            val res = Result(displayTitle = "Movie $i")
            i++
            movieList.add(res)
        }
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregister() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @get :Rule
    val activityScenarioRule = ActivityScenarioRule(MovieReviewListActivity::class.java)

    //Is Activity launching
    @Test
    fun isActivityLaunch() {
        onView(withId(R.id.rootMovies)).check(matches(isDisplayed()))
    }

    //Is Recyclerview visible
    @Test
    fun isRecyclerViewVisible() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
    }

    //Select an item and perform clicks
    @Test
    fun selectListItem_moveToDetails() {
        onView(withId(R.id.rvMovie)).perform(
            actionOnItemAtPosition<MovieReviewAdapter.MovieReviewVH>(
                POS_MOVIE,
                click()
            )
        )
        onView(withId(R.id.movieName)).check(matches(withText("Movie $POS_MOVIE")))
    }

}