package com.appgenesis.com.maintenanceapp.technician.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.fragment.BreakDownTechnicianManagerParent


class WeeklyTechnicianFragment : BreakDownTechnicianManagerParent() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("preventive", true)) {
            getPreventiveRequestData("week","assigned")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getMaintenanceBreakDownRequestData("week","assigned")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("week","assigned")
        }



    }
    companion object {

        fun newInstance(args: Bundle): WeeklyTechnicianFragment {
            val fragment = WeeklyTechnicianFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
