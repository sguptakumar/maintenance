package com.appgenesis.com.maintenanceapp.operation_manager.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.BreakDownOperationManagerParent


class TodayBreakDownFragemntOperationManager : BreakDownOperationManagerParent() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getString("type").equals("preventive", true)) {
            getAllPreventiveRequest("today")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getBreakDownRequestData("today")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("today")
        }
    }

    companion object {
        fun newInstance(args: Bundle): TodayBreakDownFragemntOperationManager {
            val fragment = TodayBreakDownFragemntOperationManager()
            fragment.arguments = args
            return fragment
        }
    }

}
