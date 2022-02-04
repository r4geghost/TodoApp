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
    private lateinit var bindingItem: ItemTodoBinding
    class MyHolder(
        itemView: View,
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(itemView)
    {
        fun drawItem(todo: Todo) {
            binding.tvTodoTitle.text = todo.title
            binding.cbDone.isChecked = todo.isChecked
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { it.isChecked }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        bindingItem = ItemTodoBinding.inflate(inflater, parent, false)
        return MyHolder(bindingItem.root, bindingItem)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val curTodo = todos[position]
        holder.drawItem(curTodo)
        bindingItem.cbDone.setOnCheckedChangeListener { _, _ ->
            curTodo.isChecked = !curTodo.isChecked
        }
    }
    override fun getItemCount(): Int {
        return todos.size
    }
}
