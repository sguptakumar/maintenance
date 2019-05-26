package com.appgenesis.com.maintenanceapp.model

data class CreateBreakdownRequest(
    val machine_number: String? = null,
    val title: String? = null,
    val request_date: String? = null,
    val request_time: String? = null,
    val priority: String? = null,
    val suggested_part: String? = null,
    val request_description: String? = null,
    val request_image: String? = null
)