package com.github.hamzaahmedkhan.spinnerdialog.callbacks

import com.github.hamzaahmedkhan.spinnerdialog.ui.single.SpinnerDialogSingleSelectAdapter

interface OnSpinnerItemClickListener {
    fun onItemClick(position: Int, anyObject: Any, singleSelectAdapter: SpinnerDialogSingleSelectAdapter)
}
