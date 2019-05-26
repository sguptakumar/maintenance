
package com.appgenesis.com.maintenanceapp.technician.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.StorePreventivemaintenanceParent

class TodayStoreFragment : StorePreventivemaintenanceParent() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreRequestData()
    }
    companion object {
        fun newInstance(args: Bundle): TodayStoreFragment {
             val fragment = TodayStoreFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
