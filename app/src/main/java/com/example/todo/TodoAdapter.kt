package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.MyHolder>()
{
    class MyHolder(val bindingItem: ItemTodoBinding) : RecyclerView.ViewHolder(bindingItem.root)
    {
        fun drawItem(todo: Todo) {
            bindingItem.tvTodoTitle.text = todo.title
            bindingItem.cbDone.isChecked = todo.isChecked
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val curTodo = todos[position]
        holder.bindingItem.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                curTodo.isChecked = cbDone.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}