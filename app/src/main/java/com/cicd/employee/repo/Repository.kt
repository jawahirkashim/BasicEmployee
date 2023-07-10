package com.cicd.employee.repo

import com.cicd.employee.data.Employee
import com.cicd.employee.data.EmployeeDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(employeeDatabase: EmployeeDatabase) {

    private val employeeDao = employeeDatabase.employeeDao()

    fun getAllEmployees(): Flow<List<Employee>> = employeeDao.getAllEmployees()

    suspend fun insertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun deleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee.name)
    suspend fun update(employee: Employee) =
        employeeDao.updateEmployee(employee.name, employee.department, employee.position,employee.id)
}