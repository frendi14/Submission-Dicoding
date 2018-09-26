package com.alfatih.submissiondikoding.feature.home.model

import com.google.gson.annotations.SerializedName

data class LeaguesModel(
        @SerializedName("idLeague")
        var id: Int,
        @SerializedName("strLeague")
        var leagues: String,
        @SerializedName("strLogo")
        var logo: String) {

    data class LeaguesResponse (val countrys: List<LeaguesModel>?)
}
