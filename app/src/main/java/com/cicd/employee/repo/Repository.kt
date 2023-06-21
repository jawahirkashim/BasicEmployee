package com.cicd.employee.repo

import com.cicd.employee.data.Employee
import com.cicd.employee.data.EmployeeDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(employeeDatabase: EmployeeDatabase){

    private val employeeDao = employeeDatabase.employeeDao()

    fun getAllEmployees(): Flow<List<Employee>> = employeeDao.getAllEmployees()

    suspend fun insertEmployee(employee:Employee) = employeeDao.insertEmployee(employee)

    suspend fun deleteEmployee(empName:String) = employeeDao.deleteEmployee(empName)
}