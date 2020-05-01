package com.github.hamzaahmedkhan.spinnerdialog.callbacks

import com.github.hamzaahmedkhan.spinnerdialog.ui.multi.SpinnerDialogMultiSelectAdapter


interface OnSpinnerItemCheckboxClickListener {
    fun onItemClick(position: Int, anyObject: Any, adapter: SpinnerDialogMultiSelectAdapter)
}
