package com.github.hamzaahmedkhan.spinnerdialog.callbacks

import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel


interface OnSpinnerOKPressedListener {
    fun onItemSelect(data: SpinnerModel, selectedPosition: Int)
}
