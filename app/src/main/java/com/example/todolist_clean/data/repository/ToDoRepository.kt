package com.example.todolist_clean.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist_clean.data.TodoDao
import com.example.todolist_clean.data.models.ToDoData

class ToDoRepository (private val todoDao: TodoDao){
    val getAllData: LiveData<List<ToDoData>> = todoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        todoDao.insertData(toDoData)
    }

}