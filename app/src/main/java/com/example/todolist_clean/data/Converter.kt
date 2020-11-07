package com.example.todolist_clean.data

import androidx.room.TypeConverter
import com.example.todolist_clean.data.models.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        //TIPS CTRL Q
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        //String to object
        return Priority.valueOf(priority)
    }
    
}