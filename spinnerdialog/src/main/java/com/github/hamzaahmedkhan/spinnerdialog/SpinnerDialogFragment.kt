package com.github.hamzaahmedkhan.spinnerdialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_spinner_popup.*
import kotlin.collections.ArrayList

/**
 * Created by khanhamza on 21-Feb-17.
 */

class SpinnerDialogFragment : DialogFragment(), OnSpinnerItemClickListener, View.OnClickListener {

    private var adapter: SpinnerDialogAdapter? = null
    private var arrData: ArrayList<SpinnerModel> = ArrayList()
    private var arrFilteredData: ArrayList<SpinnerModel> = ArrayList()
    private var onSpinnerOKPressedListener: OnSpinnerOKPressedListener? = null
    private var scrollToPosition: Int = 0
    private var selectedPosition = 0
    private var selectedSpinnerModel: SpinnerModel? = null


    var title = ""
    var searchbarHint = "type here to search..."
    var themeColorResId: Int = -1
    var buttonText: String = "OK"
    var showSearchBar = true

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


        // Assign color to header and Ok button if provided by user
        if (themeColorResId != -1) {
            contHeader.setBackgroundColor(themeColorResId)
            btnOK.setBackgroundColor(themeColorResId)
        }


        // Show Search bar if true
        if (showSearchBar){
            contSearchBar.visibility = View.VISIBLE
        } else {
            contSearchBar.visibility = View.GONE
        }

        // Set text to OK Button
        btnOK.text = buttonText

        // Set text of title
        txtTitle.text = title
        edtSearch.hint = searchbarHint

        // init Adapter
        adapter = SpinnerDialogAdapter(activity, arrFilteredData, this)

        bindView()
    }

    private fun setListeners() {
        btnOK.setOnClickListener(this)

        edtSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setFilterData(s?.toString())
            }
        })
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

    fun setFilterData(filterText:String?){
        if (filterText.isNullOrEmpty()){
            arrFilteredData.clear()
            arrFilteredData.addAll(arrData)
        } else {
            arrFilteredData.clear()
            arrFilteredData.addAll(arrData.filter { it.text.contains(filterText,true) || it.descrition.contains(filterText, true) })

        }

        adapter?.notifyDataSetChanged()
    }


    override fun onClick(v: View?) {
        if (onSpinnerOKPressedListener != null) {
            selectedSpinnerModel?.let { onSpinnerOKPressedListener!!.onItemSelect(it, selectedPosition) }
        }
        this.dismiss()
    }

    override fun onItemClick(position: Int, anyObject: Any, adapter: SpinnerDialogAdapter) {
        selectedPosition = arrData.indexOf(anyObject as SpinnerModel)

        // Set selected from all data
        arrData.forEach { it.isSelected = false }
        arrData[selectedPosition].isSelected = true

        // Set selected in Filtered data
        arrFilteredData.forEach { it.isSelected = false }
        arrFilteredData[position].isSelected = true

        selectedSpinnerModel = anyObject

        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(title: String, arrData: ArrayList<SpinnerModel>, onSpinnerOKPressedListener: OnSpinnerOKPressedListener, scrollToPosition: Int): SpinnerDialogFragment {
            val frag = SpinnerDialogFragment()
            val args = Bundle()
            frag.title = title
            frag.arrData.addAll(arrData)
            frag.arrFilteredData.addAll(arrData)
            frag.scrollToPosition = scrollToPosition
            frag.onSpinnerOKPressedListener = onSpinnerOKPressedListener
            frag.arguments = args

            return frag
        }
    }


}

