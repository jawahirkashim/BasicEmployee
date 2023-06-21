package com.cicd.employee.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicd.employee.data.Employee
import com.cicd.employee.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val allInsertedEmployees = repository.getAllEmployees()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)


    fun insertNewEmployee(empName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val employee = Employee(name = empName)
            repository.insertEmployee(employee)
        }
    }

    fun deleteEmployee(empName:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(empName)
        }
    }

}