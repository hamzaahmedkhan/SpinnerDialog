package com.github.hamzaahmedkhan

import com.github.hamzaahmedkhan.spinnerdialog.enums.ImageType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel

object Constants {


    fun sampleDataWithDescriptionAndImage(): ArrayList<SpinnerModel> {
        val arraySpinnerModel = ArrayList<SpinnerModel>()

        for (i in 1..9) {
            val spinnerModel = SpinnerModel(
                "Number $i"
            )
            spinnerModel.id = i
            spinnerModel.description = "This is Description of $i"
            if (i%2 == 0) {
                spinnerModel.imagePath("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
            } else {
                spinnerModel.imagePath(R.drawable.img_bird)
            }
            spinnerModel.imageType = ImageType.IMAGE_CIRCLE

            arraySpinnerModel.add(
                spinnerModel
            )
        }

        return arraySpinnerModel
    }


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