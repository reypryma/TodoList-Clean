package com.example.todolist_clean

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist_clean.data.ToDoDatabase
import com.example.todolist_clean.data.models.TodoModel
import com.example.todolist_clean.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//extends AndroidViewModel contain Application Context
class ToDoViewModel(application: Application): AndroidViewModel(application) {


    private val todoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository

    private val getAllData: LiveData<List<TodoModel>>


    init {
        repository = ToDoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) { repository.insertData(todoModel) }
    }


}