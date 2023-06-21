package com.cicd.employee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee (
    val name:String,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
)