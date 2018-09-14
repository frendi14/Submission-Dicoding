package com.alfatih.submissiondikoding.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.alfatih.submissiondikoding.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val postion = intent.extras.getInt("position",0)
        Glide.with(this)
                .load(resources.obtainTypedArray(R.array.club_image).getResourceId(postion,0))
                .into(dtlImage)
        dtlHeader.text = resources.getStringArray(R.array.club_name)[postion]
        dtlDetail.text = resources.getStringArray(R.array.club_detail)[postion]
    }
}
