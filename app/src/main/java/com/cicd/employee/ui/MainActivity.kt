package com.cicd.employee.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cicd.employee.R
import com.cicd.employee.data.Employee
import com.cicd.employee.databinding.ActivityMainBinding
import com.cicd.employee.ui.adapter.ListAdapter
import com.cicd.employee.util.CURRENT_EMPLOYEE
import com.cicd.employee.util.EMPLOYEE
import com.cicd.employee.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Listener, PopupMenu.OnMenuItemClickListener {

    private val viewModel: EmployeeViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var selectedEmployee: Employee

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val employee = result.data?.getSerializableExtra(EMPLOYEE) as? Employee
                employee?.let {
                    viewModel.insertNewEmployee(it)
                }
            }
        }

    private val updateEmployee =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val employee = it.data?.getSerializableExtra(EMPLOYEE) as? Employee
                employee?.let { emp ->
                    viewModel.update(emp)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addEmployeeButton.setOnClickListener {
            val intent = Intent(this, NewEmployee::class.java)
            getContent.launch(intent)
        }

        val listAdapter = ListAdapter(this)
        binding.apply {
            recyclerview.apply {
                adapter = listAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
            lifecycleScope.launch {
                viewModel.allInsertedEmployees.collect {
                    val list = it ?: return@collect
                    listAdapter.submitList(list)
                }
            }

        }
    }

    override fun onClick(employee: Employee) {
        Log.d(TAG, "onClick: ")
        val intent = Intent(this, NewEmployee::class.java)
        intent.putExtra(CURRENT_EMPLOYEE, employee)
        updateEmployee.launch(intent)
    }


    override fun onLongPress(employee: Employee, view: CardView) {
        Log.d(TAG, "onLongPress: ")
        selectedEmployee = employee
        popUpDisplay(view)
    }

    private fun popUpDisplay(view: CardView) {
        val popUp = PopupMenu(this, view)
        popUp.setOnMenuItemClickListener(this)
        popUp.inflate(R.menu.pop_up_menu)
        popUp.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete) {
            viewModel.deleteEmployee(selectedEmployee)
            return true
        }
        return false
    }
}

interface Listener {
    fun onClick(employee: Employee)
    fun onLongPress(employee: Employee, view: CardView)
}
