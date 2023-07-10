package com.cicd.employee.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cicd.employee.data.Employee
import com.cicd.employee.databinding.ItemEmployeeBinding
import com.cicd.employee.ui.Listener

class ListAdapter(private val listener: Listener) :
    ListAdapter<Employee, ViewHolder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = getItem(position)
        if (employee != null) {
            holder.bind(employee,position)
        }
    }
}