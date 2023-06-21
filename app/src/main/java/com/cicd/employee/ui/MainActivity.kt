package com.cicd.employee.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cicd.employee.databinding.ActivityMainBinding
import com.cicd.employee.ui.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: EmployeeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.insertButton.setOnClickListener {
            val newEmployee = binding.insertNewEmployee.text.toString()
            if (newEmployee.isNotEmpty()) {
                viewModel.insertNewEmployee(newEmployee)
            }
        }

        binding.deleteButton.setOnClickListener {
            val deleteEmployee = binding.deleteEmployee.text.toString()
            if (deleteEmployee.isNotEmpty()) {
                viewModel.deleteEmployee(deleteEmployee)
            }
        }

        val listAdapter = ListAdapter()
        binding.apply {
            recyclerview.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            lifecycleScope.launch {
                viewModel.allInsertedEmployees.collect {
                    val list = it ?: return@collect
                    listAdapter.submitList(list)
                }
            }

        }

    }
}