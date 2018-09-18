package com.alfatih.submissiondikoding.feature.detail.contract

import com.alfatih.submissiondikoding.feature.detail.model.DetailModel

interface DetailCallback {

    fun onLoadData(model: DetailModel)
    fun onShowProgress()
    fun onHideProgress()

}