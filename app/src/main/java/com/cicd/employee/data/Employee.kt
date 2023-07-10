package com.cicd.employee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee (
    val name:String,
    val department:String,
    val position: String,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
): java.io.Serializable