package com.move.app.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.move.app.R
import com.move.app.model.review.Result
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailsActivityTest {

    @Test
    fun isActivityLaunch() {
        onView(withId(R.id.containerDetails))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isDetailsVisible() {
        val movieName = "Annette"
        val movieHeadline = "'Annette' Review: Love Hurts"
        val movieOpeningDate = "2021-08-20"
        val movieByline = "By: A.O. Scott"
        val movieSummary =
            "Adam driver and Marion Cotillard are star-crossed lovers in the hallucinatory musical, written by Sparks and directed by Loes Carax."
        val result = Result(
            displayTitle = movieName,
            headline = movieHeadline,
            openingDate = movieOpeningDate,
            byline = movieByline,
            summaryShort = movieSummary
        )
        val intent = Intent()
        val activityScenarioRule = ActivityScenarioRule(MovieDetailsActivity::class.java)
        onView(withId(R.id.movieName)).check(matches(withText(movieName)))
        onView(withId(R.id.movieName)).check(matches(withText(movieHeadline)))
        onView(withId(R.id.movieDate)).check(matches(withText(movieOpeningDate)))
        onView(withId(R.id.movieBy)).check(matches(withText(movieByline)))
        onView(withId(R.id.movieSummary)).check(matches(withText(movieSummary)))
    }
}