package com.example.todolist_clean.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist_clean.data.models.ToDoData

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    //observe the data changes using liveData and notified it to observer
    //Wrap to live data
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

}