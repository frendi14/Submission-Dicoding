package com.alfatih.submissiondikoding.utils

import android.support.test.espresso.IdlingResource

object EsspressoIdleing {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = CountingEspresso(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

}
