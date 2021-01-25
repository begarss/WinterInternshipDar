package com.example.dartask2studentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.dartask2studentlist.model.Student
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var studentViewModel: StudentViewModel
    var studentNotExist: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentCount.text = studentViewModel.getCount().toString()
        btnAdd.setOnClickListener {
            insertData()
        }

        btnNextPage.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }

    }

    private fun insertData() {
        val fName = name.text.toString()
        val sName = surname.text.toString()
        if (checkEmptyData(fName, sName)) {

            studentCount.text = studentViewModel.getCount().toString()

            if (isStudentNotExist(fName, sName) == true) {
                val student = Student(0, fName, sName)
                studentViewModel.addStudent(student)
                name.setText("")
                surname.setText("")
                Toast.makeText(this, "$fName $sName successfully registered", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "This student already exists", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isStudentNotExist(name: String, surname: String): Boolean {

        val c = studentViewModel.checkStudent(name, surname)
        if (c > 0) {
                studentNotExist = false
        } else if (c == 0)
            studentNotExist = true


        return studentNotExist
    }

    private fun checkEmptyData(fName: String, sName: String): Boolean {
        if (TextUtils.isEmpty(fName)) {

            nameTextInputLayout.error = "Name should not be empty"
            return false
        }
        if (TextUtils.isEmpty(sName)) {

            surnameTextInputLayout.error = "Surname should not be empty"

            return false
        }
        return true
    }
}