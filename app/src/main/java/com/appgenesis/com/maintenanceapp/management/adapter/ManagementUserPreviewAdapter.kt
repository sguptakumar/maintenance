package com.appgenesis.com.maintenanceapp.management.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.model.UserPreviewModel
import com.appgenesis.com.maintenanceapp.model.MaintenanceRequest
import com.appgenesis.com.maintenanceapp.model.TechnicianRequest
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel
import kotlinx.android.synthetic.main.userpreviewadapter.view.*
import java.lang.reflect.Type

class ManagementUserPreviewAdapter(val mContext: Context?, private val technicianRequestlist:
ArrayList<TechnicianRequest>,val type: String)
    : RecyclerView.Adapter<ManagementUserPreviewAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val technicianname=view.findViewById<View>(R.id.technician_name)as TextView
        val technicianemail=view.findViewById<View>(R.id.technician_email)as TextView
        val mobile=view.findViewById<View>(R.id.technician_mobile)as TextView
        val technicianusertype=view.findViewById<View>(R.id.technician_usertype)as TextView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.userpreviewadapter, parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
       return technicianRequestlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.technicianname.text = technicianRequestlist[holder.adapterPosition].fullname
        holder.technicianemail.text = technicianRequestlist[holder.adapterPosition].email
        holder.mobile.text = technicianRequestlist[holder.adapterPosition].phone
        holder.technicianusertype.text=technicianRequestlist[holder.adapterPosition].role

    }

    fun addAllTechnicianeRequest(technicianRequests: List<TechnicianRequest>) {
        technicianRequestlist.addAll(technicianRequests)
        notifyDataSetChanged()


    }


}