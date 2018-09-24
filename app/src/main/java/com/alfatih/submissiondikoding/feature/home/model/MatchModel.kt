package com.alfatih.submissiondikoding.feature.home.model

import com.google.gson.annotations.SerializedName

data class MatchModel(
        @SerializedName("idEvent")
        var idMatch: Int,
        @SerializedName("dateEvent")
        var date: String,
        @SerializedName("strHomeTeam")
        var teamOneName: String,
        @SerializedName("intHomeScore")
        var teamOneScore: String,
        @SerializedName("strAwayTeam")
        var teamTwoName: String,
        @SerializedName("intAwayScore")
        var teamTwoScore: String,

        var isNextMatch: Int
) {
    data class ListMatchResponse(val events: List<MatchModel>?)
}