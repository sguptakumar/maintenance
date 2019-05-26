package com.appgenesis.com.maintenanceapp.model

data class CreateTechnician(
    val user_first_name: String? = null,
    val user_last_name: String? = null,
    val user_email: String? = null,
    val password: String? = null,
    val user_phone: String? = null,
    val user_image: String? = null

)