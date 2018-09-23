package com.alfatih.submissiondikoding.feature.home.contract

import com.alfatih.submissiondikoding.contract.BasePresenter
import com.alfatih.submissiondikoding.feature.home.model.MatchModel

interface MatchCallback {

    interface View{
        fun onRefreshList(list: ArrayList<MatchModel>, next: Int)
        fun onShowProgress()
        fun onHideProgress()
    }

    interface Presenter: BasePresenter<View>{
        fun getData(request: Int, id: Int)
        fun getFavorite()
    }

}