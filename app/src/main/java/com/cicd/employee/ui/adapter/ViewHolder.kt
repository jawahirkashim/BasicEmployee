package com.cicd.employee.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cicd.employee.data.Employee
import com.cicd.employee.databinding.ItemEmployeeBinding

class ViewHolder(private val itemEmployeeBinding: ItemEmployeeBinding) :
    RecyclerView.ViewHolder(itemEmployeeBinding.root) {

        fun bind(employee:Employee){
            itemEmployeeBinding.apply {
                empName.text = employee.name
            }
        }
}