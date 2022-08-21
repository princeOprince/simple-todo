package com.princeoprince.simpletodo.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.princeoprince.simpletodo.R
import com.princeoprince.simpletodo.controller.ToDoAdapter
import com.princeoprince.simpletodo.controller.ToDoDatabaseHandler
import com.princeoprince.simpletodo.databinding.ActivityMainBinding
import com.princeoprince.simpletodo.databinding.DialogToDoItemBinding
import com.princeoprince.simpletodo.model.ToDo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHandler: ToDoDatabaseHandler
    private lateinit var toDoList: ArrayList<ToDo>
    private lateinit var toDoListItems: ArrayList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHandler = ToDoDatabaseHandler(this)
        toDoList = dbHandler.readToDos()
        toDoListItems = ArrayList()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ToDoAdapter(dbHandler.readToDos(), this, binding)

        binding.fab.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogBinding = DialogToDoItemBinding.inflate(layoutInflater)

            dialog.setTitle(getString(R.string.add_todo))
            dialog.setView(dialogBinding.root)
            dialog.setPositiveButton(getString(R.string.add)) { _, _ ->
                if (dialogBinding.edtToDoName.text.isNotEmpty()) {
                    val toDo = ToDo()
                    toDo.toDoName = dialogBinding.edtToDoName.text.toString()
                    toDo.isCompleted = false
                    dbHandler.createToDo(toDo)
                    binding.recyclerView.adapter?.notifyItemInserted(toDoList.indexOf(toDo))
                }
            }
            dialog.setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            dialog.show()
        }
    }
}