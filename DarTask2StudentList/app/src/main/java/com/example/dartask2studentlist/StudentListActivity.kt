package com.example.dartask2studentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val adapter = StudentAdapter()
        val recyclerView = studentRV
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.readAllData.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}