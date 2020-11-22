package com.example.todolist_clean.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist_clean.data.models.ToDoData

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    //observe the data changes using liveData and notified it to observer
    //Wrap to live data
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)
}