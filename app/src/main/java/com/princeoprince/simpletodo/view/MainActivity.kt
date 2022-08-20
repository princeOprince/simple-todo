package com.princeoprince.simpletodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.princeoprince.simpletodo.controller.ToDoDatabaseHandler
import com.princeoprince.simpletodo.databinding.ActivityMainBinding
import com.princeoprince.simpletodo.model.ToDo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHandler: ToDoDatabaseHandler
    private lateinit var todoList: ArrayList<ToDo>
    private lateinit var toDoListItems: ArrayList<ToDo>
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}