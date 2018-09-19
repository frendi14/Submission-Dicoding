package com.alfatih.submissiondikoding.feature.detail.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


data class DetailModel(
        @SerializedName("idEvent")
        @Expose
        var idEvent: String,
        @SerializedName("strHomeTeam")
        @Expose
        var strHomeTeam: String,
        @SerializedName("strAwayTeam")
        @Expose
        var strAwayTeam: String,
        @SerializedName("intHomeScore")
        @Expose
        var intHomeScore: String,
        @SerializedName("intAwayScore")
        @Expose
        var intAwayScore: String,
        @SerializedName("strHomeGoalDetails")
        @Expose
        var strHomeGoalDetails: String,
        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        var strHomeLineupGoalkeeper: String,
        @SerializedName("strHomeLineupDefense")
        @Expose
        var strHomeLineupDefense: String,
        @SerializedName("strHomeLineupMidfield")
        @Expose
        var strHomeLineupMidfield: String,
        @SerializedName("strHomeLineupForward")
        @Expose
        var strHomeLineupForward: String,
        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        var strHomeLineupSubstitutes: String,
        @SerializedName("strHomeFormation")
        @Expose
        var strHomeFormation: String,
        @SerializedName("strAwayGoalDetails")
        @Expose
        var strAwayGoalDetails: String,
        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        var strAwayLineupGoalkeeper: String,
        @SerializedName("strAwayLineupDefense")
        @Expose
        var strAwayLineupDefense: String,
        @SerializedName("strAwayLineupMidfield")
        @Expose
        var strAwayLineupMidfield: String,
        @SerializedName("strAwayLineupForward")
        @Expose
        var strAwayLineupForward: String,
        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        var strAwayLineupSubstitutes: String,
        @SerializedName("strAwayFormation")
        @Expose
        var strAwayFormation: String,
        @SerializedName("intHomeShots")
        @Expose
        var intHomeShots: String,
        @SerializedName("intAwayShots")
        @Expose
        var intAwayShots: String,
        @SerializedName("dateEvent")
        @Expose
        var dateEvent: String,
        @SerializedName("strDate")
        @Expose
        var strDate: String,
        @SerializedName("idHomeTeam")
        var idHomeTeam: String,
        @SerializedName("idAwayTeam")
        var idAwayTeam: String
) {
        data class DetailResponse (val events: List<DetailModel>)
}