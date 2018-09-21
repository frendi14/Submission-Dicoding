package com.alfatih.submissiondikoding.feature.detail.contract

import com.alfatih.submissiondikoding.contract.BasePresenter
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel

interface DetailCallback {

    interface View{
        fun onLoadData(model: DetailModel, away: String, home: String)
        fun onShowProgress()
        fun onHideProgress()
    }

    interface Presenter: BasePresenter<View>{
        fun getDataDetail(iventId: Int)
    }
}