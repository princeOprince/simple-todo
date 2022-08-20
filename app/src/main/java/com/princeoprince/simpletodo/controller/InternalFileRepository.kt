package com.princeoprince.simpletodo.controller

import android.content.Context
import com.princeoprince.simpletodo.model.ToDo
import com.princeoprince.simpletodo.utils.noteDirectory
import com.princeoprince.simpletodo.utils.noteFile

class InternalFileRepository(var context: Context) : NoteRepository {
    override fun addNote(toDo: ToDo) {
        context.openFileOutput(toDo.fileName, Context.MODE_PRIVATE).use {
            it.write(toDo.noteText.toByteArray())
        }
    }

    override fun getNote(fileName: String): ToDo {
        val toDo = ToDo(fileName, "")
        context.openFileInput(fileName).use {
            toDo.noteText = it.bufferedReader().use { it.readText() }
        }
        return toDo
    }

    override fun deleteNote(fileName: String): Boolean {
        return noteFile(fileName, noteDirectory(context)).delete()
    }
}
