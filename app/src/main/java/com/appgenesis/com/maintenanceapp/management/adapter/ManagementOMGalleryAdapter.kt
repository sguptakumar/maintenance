package com.appgenesis.com.maintenanceapp.management.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R

class ManagementOMGalleryAdapter(val mContext: Context?, val licenceList: IntArray) : RecyclerView.Adapter<ManagementOMGalleryAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.gallery_item_layout, parent, false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return licenceList.size
    }
}