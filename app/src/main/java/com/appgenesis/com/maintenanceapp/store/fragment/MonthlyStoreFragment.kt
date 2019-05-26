
package com.appgenesis.com.maintenanceapp.technician.fragment

import android.os.Bundle
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.StorePreventivemaintenanceParent


class MonthlyStoreFragment : StorePreventivemaintenanceParent() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreRequestData()

    }

        companion object {
        fun newInstance(args: Bundle): MonthlyStoreFragment {
             val fragment = MonthlyStoreFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
