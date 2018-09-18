package com.alfatih.submissiondikoding.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.detail.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailCallback {

    private lateinit var presenter: DetailPresenter
    private var params = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        params = intent.extras?.getInt("eventId",0)!!
        presenter = DetailPresenter(this,this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getDataDetail(params)
    }

    override fun onLoadData(model: DetailModel) {

    }

    override fun onShowProgress() {

    }

    override fun onHideProgress() {

    }
}
