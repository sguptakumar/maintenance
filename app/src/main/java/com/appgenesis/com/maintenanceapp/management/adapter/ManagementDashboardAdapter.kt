package com.appgenesis.com.maintenanceapp.management.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.activity.ManagementBreakDownRequestActivity
import com.appgenesis.com.maintenanceapp.management.activity.ManagementCreatePreventiveMaintenance
import com.appgenesis.com.maintenanceapp.management.activity.ManagementMaintenanceRequestActivity
import com.appgenesis.com.maintenanceapp.management.activity.ManagementUserPreviewActivity
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel

class ManagementDashboardAdapter(val mContext: Context?, private val licenceList: ArrayList<DashboardModel>)
    : RecyclerView.Adapter<ManagementDashboardAdapter.MyViewHolder>() {



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCount: TextView = view.findViewById<View>(R.id.count) as TextView
        val tvTitle: TextView = view.findViewById<View>(R.id.title) as TextView
        val iconimage : ImageView = view.findViewById(R.id.iconimage)
        var mainbg : RelativeLayout = view.findViewById(R.id.mainbg)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (licenceList.size != 0) {
            var model = DashboardModel(name = "Breakdown Request", coutn = "30")
            var model1 = DashboardModel(name = "Maintenance Request", coutn = "20")
            var model2 = DashboardModel(name = "Preventive Maintenance", coutn = "35")
            var model3 = DashboardModel(name = "Technician", coutn = "10")
            var model4 = DashboardModel(name = "Completed Request", coutn = "35")
            var model5=  DashboardModel(name="List of Available Part",coutn = "35")
            var model6=  DashboardModel(name="Closed Request",coutn="40")
            if (licenceList[position].name.equals("Breakdown Request", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.yellowboxbg)
                holder.iconimage.setImageResource(R.drawable.breakdown)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.yellow))
            }
            if (licenceList[position].name.equals("Maintenance Request", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.maintainencerequestbg)
                holder.iconimage.setImageResource(R.drawable.maintenencerequest)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.pink))

            }
            if (licenceList[position].name.equals("Preventive Maintenance", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.preventivemaintainencebg)
                holder.iconimage.setImageResource(R.drawable.preventivemaintenence)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.blue))

            }
            if (licenceList[position].name.equals("Technician", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.technicianbg)
                holder.iconimage.setImageResource(R.drawable.technician)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.blue))

            }
            if (licenceList[position].name.equals("Completed Request", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.completedrequestbg)
                holder.iconimage.setImageResource(R.drawable.completedrequest)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.green))

            }
            if (licenceList[position].name.equals("List Of Available part", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.listofavailablepartbg)
                holder.iconimage.setImageResource(R.drawable.listofavailpart)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.blue))

            }
            if (licenceList[position].name.equals("Closed Request", true)) {
                holder.mainbg.setBackgroundResource(R.drawable.closedrequestbg)
                holder.iconimage.setImageResource(R.drawable.closedrequest)
                holder.tvCount.setTextColor(ContextCompat.getColor(mContext!!, R.color.close))

            }

            holder.tvTitle.text = licenceList[position].name
            holder.tvCount.text = licenceList[position].coutn
            holder.mainbg.setOnClickListener {
                if (position == 0) {
                    val breakDown = Intent(mContext, ManagementBreakDownRequestActivity::class.java)
                    breakDown.putExtra("type","breakdown")
                    mContext!!.startActivity(breakDown)
                }
               if (position == 1){
                    val breakDown = Intent(mContext, ManagementMaintenanceRequestActivity::class.java)
                   breakDown.putExtra("type","mr")
                    mContext!!.startActivity(breakDown)
                }
                if (position == 2){
                    val breakDown = Intent(mContext, ManagementCreatePreventiveMaintenance::class.java)
                    breakDown.putExtra("type","preventive")
                    mContext!!.startActivity(breakDown)
                }
                if (position == 3){
                    val breakDown = Intent(mContext, ManagementUserPreviewActivity::class.java)
                    mContext!!.startActivity(breakDown)
                }
               /* if (position == 4){
                    val breakDown = Intent(mContext, ClosedRequestActivityMaintenanceManager::class.java)
                    mContext!!.startActivity(breakDown)
              }*/
         }
            }

        }

        override fun getItemCount(): Int {
            return licenceList.size
        }

        interface OnItemClick {
            fun onSelect(position: Int)
        }

    }