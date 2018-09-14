package com.alfatih.submissiondikoding.feature.home.model

import com.google.gson.annotations.SerializedName

class LeaguesModel {

    @SerializedName("idLeague")
    val id = 0
    @SerializedName("strLeague")
    val leagues = ""
    @SerializedName("strLogo")
    val logo = ""

    class LeaguesResponse{

        @SerializedName("countrys")
        val list = ArrayList<LeaguesModel>()

    }
}
