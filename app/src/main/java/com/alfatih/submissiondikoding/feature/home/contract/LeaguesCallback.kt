package com.alfatih.submissiondikoding.feature.home.contract

import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel

interface LeaguesCallback {

    fun onRefreshList(list: ArrayList<LeaguesModel>)
    fun onShowProgress()
    fun onHideProgress()
}