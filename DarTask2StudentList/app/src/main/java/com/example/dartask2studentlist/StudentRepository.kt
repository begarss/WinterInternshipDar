package com.example.dartask2studentlist

import androidx.lifecycle.LiveData
import com.example.dartask2studentlist.DB.StudentDao
import com.example.dartask2studentlist.model.Student
import kotlinx.coroutines.runBlocking

class StudentRepository(private val studentDao: StudentDao) {
    val readAllData: LiveData<List<Student>> = studentDao.readAllData()
    suspend fun addStudent(student: Student) {
        studentDao.addStudent(student)
    }

    fun checkStudent(name: String, surname: String): Int = runBlocking {
         studentDao.checkExistence(name, surname)
    }

    fun getCount():Int = runBlocking {
        studentDao.getStudentCount()
    }
}