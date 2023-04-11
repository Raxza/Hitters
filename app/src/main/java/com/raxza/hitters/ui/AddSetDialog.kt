package com.raxza.hitters.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.raxza.hitters.R
import com.raxza.hitters.data.Sets

class AddSetDialog: DialogFragment() {

    private var mListener: DialogSetListener? = null
    private var mMenuSetId: Int? = 0
    private lateinit var setName: TextInputEditText
    private lateinit var setWeight: TextInputEditText
    private lateinit var setReps: TextInputEditText
    private lateinit var setSets: TextInputEditText
    private lateinit var btnAdd: Button
    private lateinit var btnCancel: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_add_set, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (arguments != null) mMenuSetId = arguments!!.getInt("menuSetId",0)
        mMenuSetId = arguments?.getInt("menuSetId",0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setName = view.findViewById(R.id.edt_set_name)
        setWeight = view.findViewById(R.id.edt_weight)
        setReps = view.findViewById(R.id.edt_rep)
        setSets = view.findViewById(R.id.edt_set)
        btnAdd = view.findViewById(R.id.btn_create_set)
        btnCancel = view.findViewById(R.id.btn_cancel)

        btnAdd.setOnClickListener {
            val name = setName.text.toString()
            val weight = setWeight.text.toString()
            val reps = setReps.text.toString()
            val sets = setSets.text.toString()
            when {
                name.isEmpty() -> setName.error = "Input sets name"
                reps.isEmpty() -> setReps.error = "Input reps count"
                sets.isEmpty() -> setSets.error = "Input sets count"
                else -> {
                    val tempSet = Sets(menuSetId = mMenuSetId!!, name = name, weight = weight, rep = reps, set = sets)
                    mListener?.onDialogSet(tag, tempSet)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as DialogSetListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (mListener != null) {
            mListener = null
        }
    }

    interface DialogSetListener {
        fun onDialogSet(tag: String?, sets: Sets)
    }
}