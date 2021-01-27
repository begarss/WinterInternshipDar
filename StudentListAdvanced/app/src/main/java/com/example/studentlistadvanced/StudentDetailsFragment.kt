package com.example.studentlistadvanced

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.studentlistadvanced.databinding.FragmentStudentDetailsBinding
import com.example.studentlistadvanced.databinding.FragmentStudentListBinding

class StudentDetailsFragment : Fragment() {

    val args: StudentDetailsFragmentArgs by navArgs()
    private var _binding: FragmentStudentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student = args.student
        binding.detId.text = student.id.toString()
        binding.detName.text = student.name
        binding.detSurname.text = student.surname
        binding.detGrade.text = student.grade.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}