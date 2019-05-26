
package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceManagerBreakdownRequestAdapter

import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList
import kotlinx.android.synthetic.main.fragment_today_break_down_fragemnt_operation_manager.*

class BreakDownRequestFragemntMaintenanaceManager : Fragment() {

    val operationManger = ArrayList<BreakDownList>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_break_down_request_operation_manager, container, false)
    }

    var type:String = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!= null){
            type = arguments!!.getString("type","")
        }
        var model = BreakDownList(title = "Breakdown Request")
        var model1 = BreakDownList(title = "Breakdown Request")
        var model2 = BreakDownList(title = "Breakdown Request")
        var model3 = BreakDownList(title = "Breakdown Request")
        var model4 = BreakDownList(title = "Breakdown Request")

        operationManger.add(model)
        operationManger.add(model1)
        operationManger.add(model2)
        operationManger.add(model3)
        operationManger.add(model4)
        recyclerView.adapter = MaintenanceManagerBreakdownRequestAdapter(activity,operationManger,type)
        recyclerView.layoutManager  = LinearLayoutManager(activity!!)
    }

        companion object {
        fun newInstance(args: Bundle): BreakDownRequestFragemntMaintenanaceManager {
             val fragment = BreakDownRequestFragemntMaintenanaceManager()
            fragment.arguments = args
            return fragment
        }
    }
}
