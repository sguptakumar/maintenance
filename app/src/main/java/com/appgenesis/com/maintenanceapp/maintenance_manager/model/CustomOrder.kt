package com.appgenesis.com.maintenanceapp.maintenance_manager.model

data class CustomOrder(
    val request_id: String? = null,
    val part_name: String? = null,
    val quantity: String? = null,
   // val comment:String?=null,
    val description: String? = null,
    val need_by_date: String? = null,
    val file: String? = null,
    val image: String? = null
)