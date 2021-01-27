package com.example.studentlistadvanced

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlistadvanced.model.Student
import kotlinx.android.synthetic.main.student_item.view.*

class StudentAdapter internal constructor(
    listener: OnStudentListener,
) : RecyclerView.Adapter<StudentViewHolder>() {
    private var listener: OnStudentListener = listener

    private var mainList = ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        when (holder) {
            is StudentViewHolder -> {
                holder.bind(mainList[position], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    fun submitList(students: ArrayList<Student>) {
        mainList = if (students.isEmpty())
            arrayListOf()
        else
            students
        notifyDataSetChanged()
    }

    fun appendList(deleted: ArrayList<Student>) {
        mainList.addAll(deleted)
        notifyDataSetChanged()
    }

    fun appendOneStudent(student: Student){
        mainList.add(student)
        notifyDataSetChanged()
    }

    fun deleteStudent(student: Student, position: Int) {
        mainList.remove(student)
        notifyItemRemoved(position)
    }

}

class StudentViewHolder internal constructor(
    itemView: View,
    onStudentListener: OnStudentListener
) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.tvName
    private val studentIndex = itemView.tvID
    private val deleteBtn = itemView.btnDelete
    fun bind(student: Student, clickListener: OnStudentListener) {
        studentIndex.text = "${student.id}"

        name.text = student.name
        deleteBtn.setOnClickListener {
            clickListener.delete(student, adapterPosition)
        }
        itemView.setOnClickListener {
            clickListener.onStudentClick(student)
        }
    }


}

interface OnStudentListener {
    fun onStudentClick(student: Student)
    fun delete(student: Student, position: Int)

}