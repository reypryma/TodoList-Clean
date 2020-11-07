package com.example.todolist_clean.data

import android.content.Context
import androidx.room.*
import com.example.todolist_clean.data.models.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): TodoDao

    //volatile writes to this field are immediately
    //made visible to other threads
    companion object{
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            //No multiple thread create multiple instance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}