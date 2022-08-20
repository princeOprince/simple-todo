package com.princeoprince.simpletodo.controller

import com.princeoprince.simpletodo.model.ToDo

interface NoteRepository {
    fun addNote(toDo: ToDo)
    fun getNote(fileName: String): ToDo
    fun deleteNote(fileName: String): Boolean
}
