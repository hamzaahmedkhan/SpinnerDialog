package com.github.hamzaahmedkhan.spinnerdialog.ui.single

import android.content.Context
import android.util.Size
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.github.hamzaahmedkhan.spinnerdialog.R
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemClickListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.ImageType
import com.github.hamzaahmedkhan.spinnerdialog.extension.gone
import com.github.hamzaahmedkhan.spinnerdialog.extension.visible
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import java.util.*

/**
 *
 */
class SpinnerDialogSingleSelectAdapter(
    private val context: Context,
    var arrData: ArrayList<SpinnerModel>,
    private val onItemClick: OnSpinnerItemClickListener,
    private val showDescription: Boolean,
    private val showImage: Boolean,
    private val imageWidth: Int?,
    private val imageHeight: Int?
) : RecyclerView.Adapter<SpinnerDialogSingleSelectAdapter.ViewHolder>() {
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
        with(holder) {
            val model =
                arrData[this.adapterPosition]
            this.txtChoice.text = model.text
            this.txtDescription.text = model.description

            // if showDescription is true, set textView visible, else gone
            if (showDescription) {
                this.txtDescription.visible()
            } else {
                this.txtDescription.gone()
            }

            loadImage(model)

            this.radioButton.isChecked = model.isSelected
            this.contParentLayout.setOnClickListener { view: View? ->
                onItemClick.onItemClick(
                    this.adapterPosition,
                    model,
                    this@SpinnerDialogSingleSelectAdapter
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

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var radioButton: RadioButton
        var txtChoice: TextView
        var txtDescription: TextView
        var contParentLayout: LinearLayout
        var imgIcon: ImageView

        init {
            radioButton = itemView.findViewById(R.id.radioButton)
            txtChoice = itemView.findViewById(R.id.txtChoice)
            txtDescription = itemView.findViewById(R.id.txtDescription)
            contParentLayout = itemView.findViewById(R.id.contParentLayout)
            imgIcon = itemView.findViewById(R.id.imgIcon)
        }
    }

}