package com.example.tictactoe

data class Player(
    val id:Int,
    val name: String,
    var wins:Int? = null,
    var loses:Int? = null,
    var ties:Int? = null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}