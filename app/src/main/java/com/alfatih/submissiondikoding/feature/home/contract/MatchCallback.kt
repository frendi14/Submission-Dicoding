package com.alfatih.submissiondikoding.feature.home.contract

import com.alfatih.submissiondikoding.feature.home.model.MatchModel

interface MatchCallback {

    fun onRefreshList(list: ArrayList<MatchModel>, next: Int)
    fun onShowProgress()
    fun onHideProgress()
}