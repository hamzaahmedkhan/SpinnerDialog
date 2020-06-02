package com.github.hamzaahmedkhan.spinnerdialog.callbacks

import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel


interface OnSpinnerOKPressedListener {
    fun onSingleSelection(data: SpinnerModel, selectedPosition: Int)
    fun onMultiSelection(data: List<SpinnerModel>, selectedPosition: Int)
}
