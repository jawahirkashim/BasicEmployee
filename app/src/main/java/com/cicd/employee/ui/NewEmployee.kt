package com.cicd.employee.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cicd.employee.data.Employee
import com.cicd.employee.databinding.ActivityNewEmployeeBinding
import com.cicd.employee.util.CURRENT_EMPLOYEE
import com.cicd.employee.util.EMPLOYEE
import com.cicd.employee.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewEmployee : AppCompatActivity() {
    private lateinit var binding: ActivityNewEmployeeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)

        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var oldData: Employee? = null
        try {
            oldData = intent.getSerializableExtra(CURRENT_EMPLOYEE) as Employee
            oldData.let {
                binding.employeeNameEt.setText(it.name)
                binding.departmentEt.setText(it.department)
                binding.positionEt.setText(it.position)
            }
        } catch (_: java.lang.Exception) {
        }


        binding.saveButton.setOnClickListener {

            val name = binding.employeeNameEt.text.toString()
            val dep = binding.departmentEt.text.toString()
            val pos = binding.positionEt.text.toString()

            if (name.isEmpty()) return@setOnClickListener

            val emp = if (oldData != null) Employee(name, dep, pos, oldData.id)
            else Employee(name, dep, pos)

            val intent = Intent()
            intent.putExtra(EMPLOYEE, emp)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.backIv.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}