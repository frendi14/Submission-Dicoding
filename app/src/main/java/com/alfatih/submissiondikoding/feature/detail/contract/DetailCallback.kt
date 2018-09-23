package com.alfatih.submissiondikoding.feature.detail.contract

import com.alfatih.submissiondikoding.contract.BasePresenter
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.home.model.MatchModel

interface DetailCallback {

    interface View{
        fun onLoadData(model: DetailModel, away: String, home: String)
        fun isExist(value: Boolean)
        fun onShowProgress()
        fun onHideProgress()
    }

    interface Presenter: BasePresenter<View>{
        fun checkingData(id: Int)
        fun getDataDetail(iventId: Int)
        fun insertFavorite(model:MatchModel)
        fun deleteFavorite(idIvent: Int)
    }
}