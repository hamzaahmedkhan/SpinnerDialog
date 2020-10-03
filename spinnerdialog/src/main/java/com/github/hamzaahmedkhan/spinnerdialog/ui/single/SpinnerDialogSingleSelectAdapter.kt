package com.github.hamzaahmedkhan.spinnerdialog.ui.single

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.github.hamzaahmedkhan.spinnerdialog.R
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemClickListener
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import java.util.*

/**
 *
 */
class SpinnerDialogSingleSelectAdapter(
    private val context: Context,
    var arrData: ArrayList<SpinnerModel>,
    private val onItemClick: OnSpinnerItemClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<SpinnerDialogSingleSelectAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView: View = LayoutInflater.from(context)
            .inflate(R.layout.item_spinner_dialog, parent, false)
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
        holder.txtChoice.text = model.text
        holder.radioButton.isChecked = model.isSelected
        holder.contParentLayout.setOnClickListener { view: View? ->
            onItemClick.onItemClick(
                holder.adapterPosition,
                model,
                this@SpinnerDialogSingleSelectAdapter
            )
        }
    }

    fun addItem(homeCategories: ArrayList<SpinnerModel>) {
        arrData = homeCategories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var radioButton: RadioButton
        var txtChoice: TextView
        var contParentLayout: LinearLayout

        init {
            radioButton = itemView.findViewById(R.id.radioButton)
            txtChoice = itemView.findViewById(R.id.txtChoice)
            contParentLayout = itemView.findViewById(R.id.contParentLayout)
        }
    }

}