
package com.appgenesis.com.maintenanceapp.management.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.fragment.ManagementPreventiveMaintenanceParent
import com.appgenesis.com.maintenanceapp.management.adapter.ManagementClosedRequestAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList
import com.appgenesis.com.maintenanceapp.technician.adapter.TechnicianClosedRequestAdapter
import kotlinx.android.synthetic.main.fragment_today_break_down_fragemnt_operation_manager.*

class MonthlyManagementFragment : ManagementPreventiveMaintenanceParent() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            getAllPreventiveRequest("month")

    }

        companion object {
        fun newInstance(args: Bundle): MonthlyManagementFragment {
             val fragment = MonthlyManagementFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
