package com.github.hamzaahmedkhan

import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel


enum class TestEnum {

    ENUM1 {
        override fun toString(): String {
            return "enum1"
        }
    },

    ENUM2 {
        override fun toString(): String {
            return "enum2"
        }
    }

}