package com.appgenesis.com.maintenanceapp.listener

import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel

interface OperationManagerListener  {
    fun onItemClickListener(data: DashboardModel)
}