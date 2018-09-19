package com.alfatih.submissiondikoding.feature.detail.contract

import com.alfatih.submissiondikoding.feature.detail.model.DetailModel

interface DetailCallback {

    fun onLoadData(model: DetailModel, away: String, home: String)
    fun onShowProgress()
    fun onHideProgress()

}