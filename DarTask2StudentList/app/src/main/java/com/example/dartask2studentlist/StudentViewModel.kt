package com.example.dartask2studentlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dartask2studentlist.DB.StudentDatabase
import com.example.dartask2studentlist.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Student>>
    private val repository: StudentRepository

    private val _existenceResponse: MutableLiveData<Int> = MutableLiveData()
    val existingStudentCount: LiveData<Int>
        get() = _existenceResponse

    val ccc = MutableLiveData<Int>()

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        readAllData = repository.readAllData
    }

    fun addStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addStudent(student)
        }
    }

//    fun checkStudent(name: String, surname: String) = viewModelScope.launch(Dispatchers.IO) {
//        _existenceResponse.postValue(repository.checkStudent(name, surname))
////        ccc.value = repository.checkStudent(name, surname)
//    }

    fun checkStudent(name: String, surname: String):Int{
        return repository.checkStudent(name, surname)
    }

    fun getCount():Int
    {
        return repository.getCount()
    }

}