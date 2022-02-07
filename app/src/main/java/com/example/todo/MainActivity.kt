package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //объект адаптера
    private lateinit var todoAdapter: TodoAdapter
    //объект байдинга
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // инциализируем байдинг
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // создаем пустой массив
        todoAdapter = TodoAdapter(mutableListOf())

        // байндим через адаптер
        binding.rvTodoList.adapter = todoAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)

        // слушатель на нажатие кнопки добавления
        binding.btnAddTodo.setOnClickListener {
            val title = binding.edTodoTitle.text.toString()
            if (title.isNotEmpty()) {
                val todo = Todo(title)
                todoAdapter.addTodo(todo)
                binding.edTodoTitle.text.clear()
                // создаем сообщение при успешном добавлении
                val toast = Toast.makeText(this, R.string.toast_add, Toast.LENGTH_SHORT)
                toast.show()
            }
        // слушатель на нажатие кнопки удаления
        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
            }
        }
    }
}