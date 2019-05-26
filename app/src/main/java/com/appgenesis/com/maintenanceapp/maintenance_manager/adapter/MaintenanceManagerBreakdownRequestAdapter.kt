package com.appgenesis.com.maintenanceapp.maintenance_manager.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList

class MaintenanceManagerBreakdownRequestAdapter(
    val mContext: Context?,
    val breakdownlist: ArrayList<BreakDownList>, val type:String)
    : RecyclerView.Adapter<MaintenanceManagerBreakdownRequestAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MaintenanceManagerBreakdownRequestAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.closed_request_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return breakdownlist.size
    }

    override fun onBindViewHolder(p0: MaintenanceManagerBreakdownRequestAdapter.MyViewHolder, p1: Int) {

    }
   /* fun addAllBreakdownRequest(breakdownRequests: List<BreakdownRequest>) {
        breakdownlist.clear()
        notifyDataSetChanged()
        breakdownlist.addAll(breakdownRequests)
        notifyDataSetChanged()
    }*/
}