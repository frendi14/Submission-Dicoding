package com.alfatih.submissiondikoding.utils

import java.text.SimpleDateFormat
import android.text.TextUtils
import java.text.ParseException
import java.util.*


object DateStringUtils {

    fun formatingWithDay(date: String): String{
        return formatting("yyyy-MM-dd", "E, dd MMMM yyyy",date)
    }

    private fun formatting(inputFormat: String, outputFormat: String, source: String): String {
        try {
            if (TextUtils.isEmpty(source)) {
                return ""
            }
            val simpleInputFormat = SimpleDateFormat(inputFormat,Locale("in","ID"))
            val simpleOutputFormat = SimpleDateFormat(outputFormat,Locale("in","ID"))
            return simpleOutputFormat.format(simpleInputFormat.parse(source))
        } catch (e: ParseException) {
            return source
        }

    }

}