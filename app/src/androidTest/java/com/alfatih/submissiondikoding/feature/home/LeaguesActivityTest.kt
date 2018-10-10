package com.alfatih.submissiondikoding.feature.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.runner.RunWith

import com.alfatih.submissiondikoding.R.id.*
import com.alfatih.submissiondikoding.utils.EsspressoIdleing
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class LeaguesActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LeaguesActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EsspressoIdleing.idlingResource)
    }

    @Test
    fun testRecyclerViewLeagues(){
        onView(ViewMatchers.withId(recyclerLeagues))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(recyclerLeagues)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(ViewMatchers.withId(recyclerLeagues)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView
                .ViewHolder>(10, ViewActions.click()))
    }

}