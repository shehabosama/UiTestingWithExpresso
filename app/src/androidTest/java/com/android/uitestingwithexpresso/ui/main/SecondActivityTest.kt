package com.android.uitestingwithexpresso.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.uitestingwithexpresso.R
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SecondActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SecondActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }
    @Test
    fun test_visibility_title_backButton(){
        onView(withId(R.id.activity_secondary_title)).check(matches(isDisplayed()))//same as bellow
        onView(withId(R.id.button_back)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))//same as above
    }
    @Test
    fun test_isTitleTextDisplayed(){
        onView(withId(R.id.activity_secondary_title)).check(matches(withText(R.string.text_secondaryactivity)))
    }

}