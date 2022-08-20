package com.princeoprince.simpletodo.model

data class ToDo(
    var toDoId: Long = -1,
    var toDoName: String = "",
    var isCompleted: Boolean = false
)