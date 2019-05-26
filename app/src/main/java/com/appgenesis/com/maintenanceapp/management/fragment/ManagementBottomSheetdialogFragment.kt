package com.appgenesis.com.maintenanceapp.management.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.activity.ManagementBreakDownRequestActivity
import com.appgenesis.com.maintenanceapp.management.activity.ManagementCreatePreventiveMaintenanceActivity
import java.lang.ClassCastException

class ManagementBottomSheetdialogFragment : BottomSheetDialogFragment() {
    private var mListner: BottomSheetListner? = null
    private var type: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.bottomsheetadapterlayout, container, false)
        return view
         //activity?.setTitle("Hello World!")





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val create = view!!.findViewById(R.id.create) as TextView
        val csvimport=view!!.findViewById(R.id.csvimport)as TextView
        val cancel=view!!.findViewById(R.id.cancel)as TextView
        val toolbar =view!!.findViewById(R.id.toolbar) as Toolbar


        create.setOnClickListener(View.OnClickListener {
            val breakDown = Intent(activity, ManagementCreatePreventiveMaintenanceActivity::class.java)
            startActivity(breakDown)


        })

    }


    interface BottomSheetListner{
        fun onButtonClicked(text:String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mListner = context as BottomSheetListner

        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString() + "must implement BottomSheetListner")
        }

    }
}