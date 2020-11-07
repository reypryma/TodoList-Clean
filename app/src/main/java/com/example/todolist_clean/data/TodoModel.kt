package com.example.todolist_clean.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="todo_table")
data  class TodoModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)