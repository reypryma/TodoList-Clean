package com.example.todolist_clean.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist_clean.data.TodoDao
import com.example.todolist_clean.data.models.TodoModel

class ToDoRepository (private val todoDao: TodoDao){
    val getAllData: LiveData<List<TodoModel>> = todoDao.getAllData()

    suspend fun insertData(todoModel: TodoModel){
        todoDao.insertData(todoModel)
    }

}