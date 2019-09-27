package com.github.hamzaahmedkhan.spinnerdialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_spinner_popup.*
import java.util.*

/**
 * Created by khanhamza on 21-Feb-17.
 */

class SpinnerDialogFragment : DialogFragment(), OnSpinnerItemClickListener, View.OnClickListener {

    private var adapter: SpinnerDialogAdapter? = null
    private lateinit var arrData: ArrayList<SpinnerModel>
    private var onSpinnerOKPressedListener: OnSpinnerOKPressedListener? = null
    private var scrollToPosition: Int = 0
    private var selectedPosition = 0
    private var selectedSpinnerModel: SpinnerModel? = null
    var title = ""
    var themeColorResId: Int = -1
    var buttonText: String = "OK"

    override fun onStart() {
        super.onStart()

        dialog.window!!
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_spinner_popup, container)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        if (themeColorResId != -1) {
            contHeader.setBackgroundColor(themeColorResId)
        }
        btnOK.text = buttonText
        txtTitle.text = title
        adapter = SpinnerDialogAdapter(activity, arrData, this)
        bindView()
    }

    private fun setListeners() {
        btnOK.setOnClickListener(this)
    }

    private fun bindView() {
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = mLayoutManager
        (recyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        val resId = R.anim.layout_animation_fall_bottom
        val animation = AnimationUtils.loadLayoutAnimation(context, resId)
        recyclerView.layoutAnimation = animation
        recyclerView.adapter = adapter
        scrollToPosition(scrollToPosition)

    }

    fun scrollToPosition(scrollToPosition: Int) {
        if (scrollToPosition > -1) {
            recyclerView.scrollToPosition(scrollToPosition)
        } else {
            recyclerView.scrollToPosition(0)
        }

    }


    override fun onClick(v: View?) {
        if (onSpinnerOKPressedListener != null) {
            selectedSpinnerModel?.let { onSpinnerOKPressedListener!!.onItemSelect(it, selectedPosition) }
        }
        this.dismiss()
    }

    override fun onItemClick(position: Int, anyObject: Any, adapter: SpinnerDialogAdapter) {
        selectedPosition = position
        for (data in arrData) {
            data.isSelected = false
        }
        arrData[position].isSelected = true

        selectedSpinnerModel = arrData[position]
        adapter.notifyDataSetChanged()

    }

    companion object {
        fun newInstance(title: String, arrData: ArrayList<SpinnerModel>, onSpinnerOKPressedListener: OnSpinnerOKPressedListener, scrollToPosition: Int): SpinnerDialogFragment {
            val frag = SpinnerDialogFragment()
            val args = Bundle()
            frag.title = title
            frag.arrData = arrData
            frag.scrollToPosition = scrollToPosition
            frag.onSpinnerOKPressedListener = onSpinnerOKPressedListener
            frag.arguments = args

            return frag
        }
    }


}

