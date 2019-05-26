package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.fragment.BreakDownMaintenanceManagerAssignParent

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AssignedBreakDownFragmentMaintenanceManager.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AssignedBreakDownFragmentMaintenanceManager.newInstance] factory method to
 * create an instance of this fragment.
 */
class AssignedBreakDownFragmentMaintenanceManager : BreakDownMaintenanceManagerAssignParent() {

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
        fun newInstance(args: Bundle): AssignedBreakDownFragmentMaintenanceManager {
            val fragment = AssignedBreakDownFragmentMaintenanceManager()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        //Inflate menu
        inflater!!.inflate(R.menu.breakdown_request_menu, menu)

    }

    //Method to Handle Option Item Click

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val selectedItem = item!!.itemId


        when (selectedItem) {
            R.id.action_today->{
                if (arguments?.getString("type").equals("preventive", true)) {
                    getPreventiveRequestData("today","assigned")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getMaintenanceBreakDownRequestData("today","assigned")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("today","assigned")
                }


            }
            R.id.action_week->{
                if (arguments?.getString("type").equals("preventive", true)) {
                    getPreventiveRequestData("week","assigned")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getMaintenanceBreakDownRequestData("week","assigned")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("week","assigned")
                }

            }
            R.id.action_month->{
                if (arguments?.getString("type").equals("preventive", true)) {
                    getPreventiveRequestData("month","assigned")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getMaintenanceBreakDownRequestData("month","assigned")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("month","assigned")
                }

            }



        }
        return super.onOptionsItemSelected(item)
    }
}
