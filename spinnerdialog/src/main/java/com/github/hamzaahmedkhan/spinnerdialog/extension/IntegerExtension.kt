package com.github.hamzaahmedkhan.spinnerdialog.extension

import android.content.res.Resources


// Integer to dp extension
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()