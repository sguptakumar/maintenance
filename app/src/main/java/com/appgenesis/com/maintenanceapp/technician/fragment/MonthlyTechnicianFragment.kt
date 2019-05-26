
package com.appgenesis.com.maintenanceapp.technician.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.BreakDownTechnicianManagerParent

class MonthlyTechnicianFragment : BreakDownTechnicianManagerParent() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("type").equals("preventive", true)) {
            getPreventiveRequestData("month","assigned")
        } else if (arguments?.getString("type").equals("breakdown", true)) {
            getMaintenanceBreakDownRequestData("month","assigned")
        }
        else if (arguments?.getString("type").equals("mr",true)) {
            getMaintenanceRequestData("month","assigned")
        }



    }
        companion object {
        fun newInstance(args: Bundle): MonthlyTechnicianFragment {
             val fragment = MonthlyTechnicianFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
