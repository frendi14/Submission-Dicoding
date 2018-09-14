package com.alfatih.submissiondikoding.feature.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.next.NextMatchFragment
import com.alfatih.submissiondikoding.feature.prev.PrevMatchFragment
import com.alfatih.submissiondikoding.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                replaceFragmentInActivity(PrevMatchFragment.newInstance(),R.id.content)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                replaceFragmentInActivity(NextMatchFragment.newInstance(),R.id.content)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
