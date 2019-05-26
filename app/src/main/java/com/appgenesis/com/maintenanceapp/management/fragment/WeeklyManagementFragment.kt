package com.appgenesis.com.maintenanceapp.management.fragment

import android.os.Bundle
import android.view.View

import com.appgenesis.com.maintenanceapp.fragment.ManagementPreventiveMaintenanceParent


class WeeklyManagementFragment : ManagementPreventiveMaintenanceParent() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            getAllPreventiveRequest("week")

    }



    companion object {

        fun newInstance(args: Bundle): WeeklyManagementFragment {
            val fragment = WeeklyManagementFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
