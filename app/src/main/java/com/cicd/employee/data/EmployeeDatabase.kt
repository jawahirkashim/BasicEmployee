package com.cicd.employee.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Employee::class],
    version = 2
)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}