package com.example.studentlistadvanced.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    val id: Int,
    val name: String?,
    val surname: String?,
    val grade: Double?,
    val image: String?
) : Parcelable {
    constructor(id: Int) : this(id, null, null, null, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }


}