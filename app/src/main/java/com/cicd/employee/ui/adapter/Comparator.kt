package com.cicd.employee.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cicd.employee.data.Employee

class Comparator: DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee) = oldItem.id==newItem.id

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee) = oldItem==newItem
}