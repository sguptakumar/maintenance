
package com.appgenesis.com.maintenanceapp.technician.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.BreakDownTechnicianManagerParent

class TodayTechnicianFragment : BreakDownTechnicianManagerParent() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("preventive", true)) {
            getPreventiveRequestData("today","assigned")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getMaintenanceBreakDownRequestData("today","assigned")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("today","assigned")
        }



    }


        companion object {
        fun newInstance(args: Bundle): TodayTechnicianFragment {
             val fragment = TodayTechnicianFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
