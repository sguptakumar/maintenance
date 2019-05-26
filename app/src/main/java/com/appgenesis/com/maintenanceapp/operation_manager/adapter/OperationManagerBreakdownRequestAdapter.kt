package com.appgenesis.com.maintenanceapp.operation_manager.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList
import com.appgenesis.com.maintenanceapp.R

class OperationManagerBreakdownRequestAdapter(val mContext: Context?,
                                              val breakdownlist: ArrayList<BreakDownList>, val type:String)
    : RecyclerView.Adapter<OperationManagerBreakdownRequestAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OperationManagerBreakdownRequestAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.closed_request_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return breakdownlist.size
    }

    override fun onBindViewHolder(p0: OperationManagerBreakdownRequestAdapter.MyViewHolder, p1: Int) {

    }
}