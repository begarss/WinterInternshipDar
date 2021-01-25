package com.example.dartask2studentlist.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dartask2studentlist.model.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Student>>

    @Query("SELECT COUNT() FROM student_table WHERE first_name=:name AND last_name=:surname")
     suspend fun checkExistence(name: String, surname: String):Int

    @Query("SELECT COUNT() FROM student_table")
    suspend fun getStudentCount():Int
}