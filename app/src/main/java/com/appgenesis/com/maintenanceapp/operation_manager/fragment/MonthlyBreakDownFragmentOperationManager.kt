package com.appgenesis.com.maintenanceapp.operation_manager.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.BreakDownOperationManagerParent


class MonthlyBreakDownFragmentOperationManager : BreakDownOperationManagerParent() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getString("type").equals("preventive", true)) {
            getAllPreventiveRequest("month")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getBreakDownRequestData("month")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("month")
        }
    }

    companion object {
        fun newInstance(args: Bundle): MonthlyBreakDownFragmentOperationManager {
            val fragment = MonthlyBreakDownFragmentOperationManager()
            fragment.arguments = args
            return fragment
        }
    }
}
