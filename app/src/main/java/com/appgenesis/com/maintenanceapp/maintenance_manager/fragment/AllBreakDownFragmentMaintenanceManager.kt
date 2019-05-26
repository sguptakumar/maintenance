package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.fragment.BreakDownMaintenanceManagerAllParent

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AllBreakDownFragmentMaintenanceManager.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AllBreakDownFragmentMaintenanceManager.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllBreakDownFragmentMaintenanceManager : BreakDownMaintenanceManagerAllParent() {


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
        fun newInstance(args: Bundle): AllBreakDownFragmentMaintenanceManager {
            val fragment = AllBreakDownFragmentMaintenanceManager()
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
                    getAllPreventiveRequest("today")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getBreakDownRequestData("today")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("today")
                }

            }
            R.id.action_week->{
                if (arguments?.getString("type").equals("preventive", true)) {
                    getAllPreventiveRequest("week")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getBreakDownRequestData("week")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("week")
                }

            }
            R.id.action_month->{
                if (arguments?.getString("type").equals("preventive", true)) {
                    getAllPreventiveRequest("month")
                } else if (arguments?.getString("type").equals("breakdown", true)) {
                    getBreakDownRequestData("month")
                }
                else if (arguments?.getString("type").equals("mr",true)) {
                    getMaintenanceRequestData("month")
                }

            }



        }
        return super.onOptionsItemSelected(item)
    }

}
