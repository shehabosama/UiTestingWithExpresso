package com.android.uitestingwithexpresso.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.uitestingwithexpresso.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigation {
    @Test
    fun test_movieNavigation(){
        //Host of the fragments
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //VERIFY
        onView(withId(R.id.movie_fragment_parent_container)).check(matches(isDisplayed()))

        // preform action to next starts fragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        //VERIFY
        onView(withId(R.id.movie_start_fragment_parent_container)).check(matches(isDisplayed()))

        // get Back
        pressBack()

        //VERIFY
        onView(withId(R.id.movie_fragment_parent_container)).check(matches(isDisplayed()))

        // preform action to next directors fragment
        onView(withId(R.id.movie_directiors)).perform(click())

        //VERIFY
        onView(withId(R.id.movie_directors_fragment_parent_container)).check(matches(isDisplayed()))

    }
}