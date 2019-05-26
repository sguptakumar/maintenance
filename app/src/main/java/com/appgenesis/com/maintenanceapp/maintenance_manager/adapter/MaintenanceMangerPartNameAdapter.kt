package com.appgenesis.com.maintenanceapp.maintenance_manager.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.store.pojo.StandardPart


class MaintenanceMangerPartNameAdapter(val mContext: Context?, val licenceList: ArrayList<com.appgenesis.com.maintenanceapp.operation_manager.model.StandardPart>)
    : RecyclerView.Adapter<MaintenanceMangerPartNameAdapter.MyViewHolder>() {
  //  private var partName: ArrayList<PartName> = ArrayList()


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_part_name: TextView = view.findViewById<View>(R.id.tv_part_name) as TextView
        val tv_part_quantity: TextView = view.findViewById<View>(R.id.tv_part_quantity) as TextView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.part_name, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


            holder.tv_part_name.text = licenceList[position].partName
            holder.tv_part_quantity.text = licenceList[position].quantity


    }

    override fun getItemCount(): Int {
        return licenceList.size
    }
    fun addAllStandardpart(standardPart: List<com.appgenesis.com.maintenanceapp.operation_manager.model.StandardPart>?) {
        licenceList.clear()
        notifyDataSetChanged()
        if (standardPart != null) {
            licenceList.addAll(standardPart)
        }
        notifyDataSetChanged()

    }
}