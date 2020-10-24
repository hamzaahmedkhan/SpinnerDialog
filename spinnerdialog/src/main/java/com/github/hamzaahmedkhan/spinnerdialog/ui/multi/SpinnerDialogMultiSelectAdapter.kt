package com.github.hamzaahmedkhan.spinnerdialog.ui.multi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.github.hamzaahmedkhan.spinnerdialog.R
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemCheckboxClickListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.ImageType
import com.github.hamzaahmedkhan.spinnerdialog.extension.gone
import com.github.hamzaahmedkhan.spinnerdialog.extension.visible
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.single.SpinnerDialogSingleSelectAdapter
import java.util.*

/**
 */
class SpinnerDialogMultiSelectAdapter(
    private val context: Context,
    var arrData: ArrayList<SpinnerModel>,
    private val onItemClick: OnSpinnerItemCheckboxClickListener,
    private val showDescription: Boolean,
    private val showImage: Boolean,
    private val imageWidth: Int?,
    private val imageHeight: Int?
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

            loadImage(model)

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


    private fun ViewHolder.loadImage(
        model: SpinnerModel
    ) {
        if (showImage) {
            this.imgIcon.visible()

            when (model.imageType) {
                ImageType.IMAGE_CIRCLE -> {

                    if (model.getImagePathResId() != -1) {
                        imgIcon.load(model.getImagePathResId()) {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                            networkCachePolicy(CachePolicy.ENABLED)
                            diskCachePolicy(CachePolicy.ENABLED)
                            placeholder(R.drawable.img_placeholder)
                            error(R.drawable.img_error)
                            build()
                        }
                    } else {
                        imgIcon.load(model.getImagePathURL()) {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                            networkCachePolicy(CachePolicy.ENABLED)
                            diskCachePolicy(CachePolicy.ENABLED)
                            placeholder(R.drawable.img_placeholder)
                            error(R.drawable.img_error)
                            build()
                        }
                    }
                }
                ImageType.IMAGE_SQUARE -> {
                    if (model.getImagePathResId() != -1) {
                        imgIcon.load(model.getImagePathResId()) {
                            crossfade(true)
                            networkCachePolicy(CachePolicy.ENABLED)
                            diskCachePolicy(CachePolicy.ENABLED)
                            placeholder(R.drawable.img_placeholder)
                            error(R.drawable.img_error)
                            build()
                        }
                    } else {
                        imgIcon.load(model.getImagePathURL()) {
                            crossfade(true)
                            networkCachePolicy(CachePolicy.ENABLED)
                            diskCachePolicy(CachePolicy.ENABLED)
                            placeholder(R.drawable.img_placeholder)
                            error(R.drawable.img_error)
                            build()
                        }
                    }
                }
            }

            imgIcon.layoutParams.apply {
                imageWidth?.let { width = it }
                imageHeight?.let { height = it }
            }
        } else {
            this.imgIcon.gone()
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
        var imgIcon: ImageView

        init {
            checkbox = view.findViewById(R.id.checkbox)
            txtChoice = view.findViewById(R.id.txtChoice)
            txtDescription = view.findViewById(R.id.txtDescription)
            contParentLayout = view.findViewById(R.id.contParentLayout)
            imgIcon = itemView.findViewById(R.id.imgIcon)
        }
    }

}