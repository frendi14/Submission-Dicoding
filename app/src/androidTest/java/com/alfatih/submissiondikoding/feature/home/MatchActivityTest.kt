package com.alfatih.submissiondikoding.feature.home

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.alfatih.submissiondikoding.R.id.*
import com.alfatih.submissiondikoding.utils.EsspressoIdleing
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchActivityTest{

    @Rule
    @JvmField
    var actvityRule = ActivityTestRule(MatchActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EsspressoIdleing.idlingResource)
    }

    @Test
    fun testRecyclerViewMatch(){
        Espresso.onView(ViewMatchers.withId(recyclerMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(recyclerMatch)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(10))
        Espresso.onView(ViewMatchers.withId(recyclerMatch)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView
                .ViewHolder>(10, ViewActions.click()))
    }
}