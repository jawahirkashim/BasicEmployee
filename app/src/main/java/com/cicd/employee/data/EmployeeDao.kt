package com.cicd.employee.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun getAllEmployees():Flow<List<Employee>>

    @Query("DELETE FROM employee WHERE name := empName")
    suspend fun deleteEmployee(empName:String)
}