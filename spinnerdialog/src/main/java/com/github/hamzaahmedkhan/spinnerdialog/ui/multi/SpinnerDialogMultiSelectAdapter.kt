package com.github.hamzaahmedkhan.spinnerdialog.ui.multi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.github.hamzaahmedkhan.spinnerdialog.R
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemCheckboxClickListener
import com.github.hamzaahmedkhan.spinnerdialog.extension.gone
import com.github.hamzaahmedkhan.spinnerdialog.extension.visible
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import java.util.*

/**
 */
class SpinnerDialogMultiSelectAdapter(
    private val context: Context,
    var arrData: ArrayList<SpinnerModel>,
    private val onItemClick: OnSpinnerItemCheckboxClickListener,
    private val showDescription: Boolean
) : androidx.recyclerview.widget.RecyclerView.Adapter<SpinnerDialogMultiSelectAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(context)
            .inflate(R.layout.item_spinner_dialog_with_checkbox, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        i: Int
    ) {
        val model =
            arrData[holder.adapterPosition]
        with(holder){
            this.txtChoice.text = model.text
            this.txtDescription.text = model.description

            // if showDescription is true, set textView visible, else gone
            if (showDescription) {
                this.txtDescription.visible()
            } else {
                this.txtDescription.gone()
            }


            this.checkbox.isChecked = model.isSelected
            this.contParentLayout.setOnClickListener { view: View? ->
                this.checkbox.isChecked = !holder.checkbox.isChecked
                model.isSelected = !model.isSelected
                onItemClick.onItemClick(
                    this.adapterPosition,
                    model,
                    this@SpinnerDialogMultiSelectAdapter
                )
            }
        }

    }

    fun addItem(homeCategories: ArrayList<SpinnerModel>) {
        arrData = homeCategories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var checkbox: CheckBox
        var txtChoice: TextView
        var txtDescription: TextView
        var contParentLayout: LinearLayout

        init {
            checkbox = view.findViewById(R.id.checkbox)
            txtChoice = view.findViewById(R.id.txtChoice)
            txtDescription = view.findViewById(R.id.txtDescription)
            contParentLayout = view.findViewById(R.id.contParentLayout)
        }
    }

}