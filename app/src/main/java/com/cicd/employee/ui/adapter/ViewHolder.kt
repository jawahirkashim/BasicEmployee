package com.cicd.employee.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cicd.employee.R
import com.cicd.employee.data.Employee
import com.cicd.employee.databinding.ItemEmployeeBinding
import com.cicd.employee.ui.Listener

class ViewHolder(
    private val itemEmployeeBinding: ItemEmployeeBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(itemEmployeeBinding.root) {

    fun bind(employee: Employee, position: Int) {
        itemEmployeeBinding.apply {
            empName.text = employee.name
            empDep.text = employee.department
            empPosition.text = employee.position
            cardView.setCardBackgroundColor(itemView.resources.getColor(getColor(position),null))

            cardView.setOnClickListener {
                it?.let {
                    listener.onClick(employee)
                }
            }

            cardView.setOnLongClickListener {
                it?.let {
                    listener.onLongPress(employee, cardView)
                }
                true
            }

        }
    }

    private fun getColor(position: Int): Int {
        val list = ArrayList<Int>()
        list.add(R.color.color_1)
        list.add(R.color.color_2)
        list.add(R.color.color_3)

        return list[position%3]
    }
}