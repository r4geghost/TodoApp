package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())

        binding.rvTodoList.adapter = todoAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val title = binding.edTodoTitle.text.toString()
            if (title.isNotEmpty()) {
                val todo = Todo(title)
                todoAdapter.addTodo(todo)
                binding.edTodoTitle.text.clear()
            }

        binding.btnDeleteTodo.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }
        }
    }
}