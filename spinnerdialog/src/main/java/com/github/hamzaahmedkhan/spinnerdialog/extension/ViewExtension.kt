package com.github.hamzaahmedkhan.spinnerdialog.extension

import android.os.Handler
import android.view.View

fun View.disableClick(time: Long = 2000) {
    this.let {
        it.isEnabled = false
        val handler = Handler()
        handler.postDelayed({
            it.isEnabled = true
        }, time)
    }
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

