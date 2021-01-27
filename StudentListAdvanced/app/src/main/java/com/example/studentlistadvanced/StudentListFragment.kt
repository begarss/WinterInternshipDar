package com.example.studentlistadvanced

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlistadvanced.databinding.FragmentStudentListBinding
import com.example.studentlistadvanced.model.Student


class StudentListFragment : Fragment(), OnStudentListener {

    private var db = StudentDataSource.getDataSource()
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.addBtn.setOnClickListener {
            insertData()
        }
        binding.restoreBtn.setOnClickListener {
            adapter.appendList(db.restore())
        }
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertData() {
        val name = binding.name.text.toString()
        if (checkEmptyData(name)) {
            val student =
                Student(db.getId(), name, "Hardcoded", 5.0, db.BASE_64_CODE)
            if (db.isStudentExist(student)) {
                Toast.makeText(requireContext(), "This student already exists", Toast.LENGTH_SHORT)
                    .show()
            } else {
                db.addStudent(student)
                Log.d("LOL", "setAdapter: ${db.getAllStudents()} ")

                adapter.submitList(db.getAllStudents())

            }
        }
    }

    private fun checkEmptyData(fName: String): Boolean {
        if (TextUtils.isEmpty(fName)) {

            binding.nameTextInputLayout.error = "Name should not be empty"
            return false
        }

        return true
    }

    private fun setAdapter() {
        adapter = StudentAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.studentRV.layoutManager = layoutManager
        adapter.submitList(db.getAllStudents())
        Log.d("LOL", "setAdapter: ${db.getAllStudents()} ")
        binding.studentRV.adapter = adapter
    }

    override fun onStudentClick(student: Student) {
        findNavController().navigate(
            StudentListFragmentDirections.actionStudentListFragmentToStudentDetailsFragment(
                student
            )
        )
    }

    override fun delete(student: Student, position: Int) {
        adapter.deleteStudent(student, position)
        db.deleteStudent(student)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}