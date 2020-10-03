package com.github.hamzaahmedkhan

import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel

object Constants {
    fun sampleDataWithDescription(): ArrayList<SpinnerModel> {
        val arraySpinnerModel = ArrayList<SpinnerModel>()

        for (i in 1..9) {
            val spinnerModel = SpinnerModel(
                "Number $i"
            )
            spinnerModel.id = i
            spinnerModel.description = "This is Description of $i"
            arraySpinnerModel.add(
                spinnerModel
            )
        }

        return arraySpinnerModel
    }


    fun sampleData(): ArrayList<SpinnerModel> {
        val arraySpinnerModel = ArrayList<SpinnerModel>()

        for (i in 1..9) {
            val spinnerModel = SpinnerModel(
                "Number $i"
            )
            spinnerModel.id = i
            arraySpinnerModel.add(
                spinnerModel
            )
        }

        return arraySpinnerModel
    }
}