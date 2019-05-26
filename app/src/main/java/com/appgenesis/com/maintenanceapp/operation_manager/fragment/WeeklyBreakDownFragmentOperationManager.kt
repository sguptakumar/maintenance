package com.appgenesis.com.maintenanceapp.operation_manager.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.BreakDownOperationManagerParent


class WeeklyBreakDownFragmentOperationManager : BreakDownOperationManagerParent() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getString("type").equals("preventive", true)) {
            getAllPreventiveRequest("week")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getBreakDownRequestData("week")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("week")
        }
    }

    companion object {

        fun newInstance(args: Bundle): WeeklyBreakDownFragmentOperationManager {
            val fragment = WeeklyBreakDownFragmentOperationManager()
            fragment.arguments = args
            return fragment
        }
    }
}
