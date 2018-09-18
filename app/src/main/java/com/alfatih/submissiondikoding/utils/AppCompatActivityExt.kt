package com.alfatih.submissiondikoding.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

/**
 * Created by Frendi on 02/11/2017.
 */

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.GONE
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int){
    supportFragmentManager.transact {
        replace(frameId,fragment)
    }
}

fun Context.toast(message: CharSequence, type: Int) {
    val toastMessage = Toast.makeText(this,"$message", Toast.LENGTH_SHORT)
    toastMessage.show()
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit){
    beginTransaction().apply{
        action()
    }.commit()
}

