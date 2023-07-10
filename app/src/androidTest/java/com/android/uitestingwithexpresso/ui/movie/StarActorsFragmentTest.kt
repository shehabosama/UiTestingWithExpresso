package com.android.uitestingwithexpresso.ui.movie

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.uitestingwithexpresso.R
import com.codingwithmitch.espressouitestexamples.ui.movie.StarActorsFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest{
    @Test
    fun test_isStartsListVisible(){
        val listOfStars = arrayListOf<String>("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors",listOfStars)
        val fragmentFactory = FragmentFactory()
        val scenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        onView(withId(R.id.star_actors_text)).check(matches(withText(StarActorsFragment.stringBuilderForStarActors(listOfStars))))
    }
}