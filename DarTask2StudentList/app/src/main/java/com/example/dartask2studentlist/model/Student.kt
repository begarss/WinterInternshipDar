package com.example.dartask2studentlist.model

import android.icu.text.StringSearch
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "student_table", indices = arrayOf(
        Index(
            value = ["first_name", "last_name"],
            unique = true
        )
    )
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "first_name") val name: String?,
    @ColumnInfo(name = "last_name") val surname: String?
)