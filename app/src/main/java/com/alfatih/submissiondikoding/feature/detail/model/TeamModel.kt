package com.alfatih.submissiondikoding.feature.detail.model

import com.google.gson.annotations.SerializedName

data class TeamModel(
        @SerializedName("strTeamBadge")
        var teamBadge: String
) {
    data class TeamResponse(var teams: List<TeamModel>?)
}