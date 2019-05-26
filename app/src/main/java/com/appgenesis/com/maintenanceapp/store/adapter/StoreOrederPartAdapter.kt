package com.appgenesis.com.maintenanceapp.store.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.store.pojo.OrderPart
import java.util.ArrayList

class StoreOrederPartAdapter(
    val mContext:Context?,
    val licenceList: ArrayList<OrderPart>
):RecyclerView.Adapter<StoreOrederPartAdapter.MyViewHolder>() {
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val partname=view.findViewById<View>(R.id.camshaft1)as TextView
        val quantity=view.findViewById<View>(R.id.quantity2)as TextView
        val needdate=view.findViewById<View>(R.id.need_date1)as TextView
        val description=view.findViewById<View>(R.id.lorel_ishpum)as TextView
        val status=view.findViewById<View>(R.id.pending)as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StoreOrederPartAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.orederpartlayout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return licenceList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.partname.text=licenceList[holder.adapterPosition].partName
        holder.quantity.text=licenceList[holder.adapterPosition].quantity
        holder.needdate.text=licenceList[holder.adapterPosition].needByDate
        holder.description.text=licenceList[holder.adapterPosition].description
        holder.status.text=licenceList[holder.adapterPosition].status

    }
    fun addAllOrderpart(orderPart: List<OrderPart>?) {
        licenceList.clear()
        notifyDataSetChanged()
        if (orderPart != null) {
            licenceList.addAll(orderPart)
        }
        notifyDataSetChanged()

    }

}