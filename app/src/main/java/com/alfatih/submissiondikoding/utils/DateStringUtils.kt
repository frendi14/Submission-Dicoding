package com.alfatih.submissiondikoding.utils

import java.text.SimpleDateFormat
import android.text.TextUtils
import java.text.ParseException
import java.util.*


object DateStringUtils {

    fun formatting(source: String): String {
        return try {
            val simpleInputFormat = SimpleDateFormat("yyyy-MM-dd",Locale("in","ID"))
            val simpleOutputFormat = SimpleDateFormat("E, dd MMMM yyyy",Locale("in","ID"))
            simpleOutputFormat.format(simpleInputFormat.parse(source))
        } catch (e: ParseException) {
            source
        }

    }

}