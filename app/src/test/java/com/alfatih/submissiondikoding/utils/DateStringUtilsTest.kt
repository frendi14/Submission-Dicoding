package com.alfatih.submissiondikoding.utils

import org.junit.Test

import org.junit.Assert.*

class DateStringUtilsTest {

    @Test
    fun formating() {
        assertEquals("Sen, 08 Oktober 2018", DateStringUtils.formatting("2018-10-08"))
    }
}