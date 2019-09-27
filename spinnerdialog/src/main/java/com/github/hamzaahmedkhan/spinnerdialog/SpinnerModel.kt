package com.github.hamzaahmedkhan.spinnerdialog

class SpinnerModel(var text: String) {

    var id: Int = 0
    var isSelected = false
    var positionInList = 0
    var descrition = ""

    override fun toString(): String {
        return text
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj is SpinnerModel) {
            obj.text == text
        } else super.equals(obj)
    }
}
