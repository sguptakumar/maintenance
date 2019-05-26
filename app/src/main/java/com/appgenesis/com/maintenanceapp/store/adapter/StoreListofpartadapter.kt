package com.appgenesis.com.maintenanceapp.store.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.store.model.StoreListRequest
import com.appgenesis.com.maintenanceapp.store.model.listofpartmodel

class StoreListofpartadapter (val mContext: Context?, private val licenceList: ArrayList<StoreListRequest>) : RecyclerView.Adapter<StoreListofpartadapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listofpartadapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return licenceList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.partname.text=licenceList[position].partname
        holder.quantity.text=licenceList[position].quantity

    }

    fun addAlllistpart(storelist: List<StoreListRequest>) {
        licenceList.clear()
        notifyDataSetChanged()
        licenceList.addAll(storelist)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val partname=view.findViewById<View>(R.id.partnametext)as TextView
      val quantity=view.findViewById<View>(R.id.quantity)as TextView

    }



}