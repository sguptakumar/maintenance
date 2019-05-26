package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.AlertDialogAdapter
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.AlertDialog
import kotlinx.android.synthetic.main.maintenance_alert_dialog_item.*

class ExtrapartFragmentMaintenanceManager :Fragment() {
    var list = ArrayList<AlertDialog>()
    protected lateinit var listpartAdapter: AlertDialogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.maintenance_alert_dialog_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var item = AlertDialog("", "", "", "")
        var item1 = AlertDialog("", "", "", "")
        var item2 = AlertDialog("", "", "", "")
        var item3 = AlertDialog("", "", "", "")
        list.add(item)
        list.add(item1)
        list.add(item2)
        list.add(item3)
        listpartAdapter = AlertDialogAdapter(this!!.activity!!, list)
        alert_dialog_recyclerView.adapter=listpartAdapter
         alert_dialog_recyclerView.layoutManager = LinearLayoutManager(activity)
    }
}