package com.appgenesis.com.maintenanceapp.technician.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.PartName
import com.appgenesis.com.maintenanceapp.operation_manager.model.StandardPart
import kotlinx.android.synthetic.main.part_name.view.*


class TechnicianPartNameAdapter(val mContext: Context?, val licenceList: ArrayList<StandardPart>)
    : RecyclerView.Adapter<TechnicianPartNameAdapter.MyViewHolder>() {
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

        if (licenceList.size != 0) {
            var model = PartName(part_name = "Camshaft", quantity = "1")
            var model1 = PartName(part_name = "Bearing", quantity = "4")
            var model2 = PartName(part_name = "Gear", quantity = "1")
            var model3 = PartName(part_name = "Intake", quantity = "2")
            var model4 = PartName(part_name = "Roller", quantity = "3")
//            partName.add(model)
//            partName.add(model1)
//            partName.add(model2)
//            partName.add(model3)
//            partName.add(model4)
            holder.tv_part_name.text = licenceList[position].partName
            holder.tv_part_quantity.text = licenceList[position].quantity

        }
    }

    override fun getItemCount(): Int {
        return licenceList.size
    }

    fun addAllStandardpart(standardorder: List<StandardPart>) {
        licenceList.clear()
        notifyDataSetChanged()
        if (standardorder != null) {
            licenceList.addAll(standardorder)
        }
        notifyDataSetChanged()


    }
}