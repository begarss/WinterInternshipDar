package com.example.dartask2studentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dartask2studentlist.model.Student
import kotlinx.android.synthetic.main.student_item.view.*
import java.util.zip.Inflater


class StudentAdapter() : RecyclerView.Adapter<StudentViewHolder>() {

    private var mainList :ArrayList<Student> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        when(holder){
            is StudentViewHolder->{
                holder.bind(mainList[position],position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    fun submitList(students:List<Student>){
        mainList = students as ArrayList<Student>
        notifyDataSetChanged()
    }

}

class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name = itemView.tvName
    private val surname = itemView.tvSurname
    private val studentIndex = itemView.tvTitle
    fun bind(student: Student, position: Int) {
        val index = position + 1
        studentIndex.text = "Student $index "

        name.text = student.name
        surname.text = student.surname
    }
}