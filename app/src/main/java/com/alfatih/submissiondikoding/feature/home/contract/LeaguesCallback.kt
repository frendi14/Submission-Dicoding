package com.alfatih.submissiondikoding.feature.home.contract

import com.alfatih.submissiondikoding.contract.BasePresenter
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel

interface LeaguesCallback {

    interface View{
        fun onRefreshList(list: ArrayList<LeaguesModel>)
        fun onShowProgress()
        fun onHideProgress()
    }

    interface Presenter: BasePresenter<View>{
        fun getDataLeagues()
    }
}