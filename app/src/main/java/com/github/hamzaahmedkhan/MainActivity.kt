package com.github.hamzaahmedkhan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.ui.single.SpinnerDialogFragment
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()

        for (i in 1..9) {
            arraySpinnerModel.add(
                SpinnerModel(
                    "Number $i"
                )
            )
        }


        // Init Fragment
        val spinnerDialogFragment =
            SpinnerDialogFragment.newInstance(
                "Spinner Dialog", arraySpinnerModel,
                object :
                    OnSpinnerOKPressedListener {
                    override fun onItemSelect(data: SpinnerModel, selectedPosition: Int) {
                        Toast.makeText(applicationContext, data.text, Toast.LENGTH_LONG).show()
                    }

                }, 0
            )



        // Using optional features
        spinnerDialogFragment.buttonText = "SAVE"
        spinnerDialogFragment.themeColorResId = resources.getColor(R.color.material_pink500)
        spinnerDialogFragment.showSearchBar = true
        spinnerDialogFragment.searchbarHint = "type here to search.."


        txtShowSingleChoiceSpinner.setOnClickListener { spinnerDialogFragment.show(supportFragmentManager, "SpinnerDialogFragment") }
        txtShowMultiChoiceSpinner.setOnClickListener { Toast.makeText(applicationContext, "In Progress", Toast.LENGTH_LONG).show() }
    }
}
