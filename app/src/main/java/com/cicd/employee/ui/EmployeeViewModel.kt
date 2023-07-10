package com.cicd.employee.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicd.employee.data.Employee
import com.cicd.employee.repo.Repository
import com.cicd.employee.util.TAG
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


    fun insertNewEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEmployee(employee)
        }
    }

    fun deleteEmployee(empName:Employee){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(empName)
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
        super.onCleared()
    }

    fun update(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO){
            repository.update(employee)
        }
    }

}