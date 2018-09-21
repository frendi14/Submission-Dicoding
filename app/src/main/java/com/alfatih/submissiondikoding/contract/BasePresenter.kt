package com.alfatih.submissiondikoding.contract

interface BasePresenter<view> {
    fun onAttach(View: view)
    fun onDetach()
}
