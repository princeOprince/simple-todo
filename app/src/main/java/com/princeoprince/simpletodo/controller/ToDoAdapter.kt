package com.princeoprince.simpletodo.controller

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.princeoprince.simpletodo.R
import com.princeoprince.simpletodo.databinding.ActivityMainBinding
import com.princeoprince.simpletodo.databinding.DialogToDoItemBinding
import com.princeoprince.simpletodo.databinding.ToDoListItemBinding
import com.princeoprince.simpletodo.model.ToDo

class ToDoAdapter(
    private val list: ArrayList<ToDo>,
    private val context: Context,
    private val contextBinding: ActivityMainBinding
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>(){

    private lateinit var dbHandler: ToDoDatabaseHandler

    override fun onCreateViewHolder(parent: ViewGroup, position: Int)
        : ToDoAdapter.ViewHolder {

        val binding = ToDoListItemBinding.inflate(
            LayoutInflater.from(context), parent, false)
        dbHandler = ToDoDatabaseHandler(context)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var binding: ToDoListItemBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

            fun bindViews(toDo: ToDo) {
                binding.txtToDoName.text = toDo.toDoName
                binding.chkToDoCompleted.isChecked = toDo.isCompleted

                binding.chkToDoCompleted.setOnCheckedChangeListener{ compoundButton, _ ->
                    toDo.isCompleted = compoundButton.isChecked
                    dbHandler.updateToDo(toDo)
                }

                binding.imgDelete.setOnClickListener(this)
                binding.imgEdit.setOnClickListener(this)
            }

        override fun onClick(imgButton: View?) {

            val position: Int = adapterPosition
            val toDo = list[position]

            when (imgButton!!.id) {
                binding.imgDelete.id -> {
                    dbHandler.deleteToDo(toDo.toDoId)
                    list.removeAt(position)
                    notifyItemRemoved(position)
                }
                binding.imgEdit.id -> {
                    editToDo(toDo)
                }
            }
        }

        private fun editToDo(toDo: ToDo) {

            val dialog = AlertDialog.Builder(context)
            val binding = DialogToDoItemBinding.inflate(
                LayoutInflater.from(context), contextBinding.root, false)

            dialog.setTitle(context.getString(R.string.update_todo))
            binding.edtToDoName.setText(toDo.toDoName)
            dialog.setView(binding.root)
            dialog.setPositiveButton(context.getString(R.string.update)) { _, _ ->
                if (binding.edtToDoName.text.isNotEmpty()) {
                    toDo.toDoName = binding.edtToDoName.text.toString()
                    dbHandler.updateToDo(toDo)
                    notifyItemChanged(list.indexOf(toDo))
                }
            }
            dialog.setNegativeButton(context.getString(R.string.cancel)){ _, _ -> }
            dialog.show()
        }
    }
}