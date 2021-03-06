package com.example.todolist_clean.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.todolist_clean.R
import com.example.todolist_clean.data.models.Priority

class SharedViewModel(application : Application): AndroidViewModel(application) {
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long) {
                when(position){

                    //? null mark
                    0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(getApplication(), R.color.red))}
                    1 -> {(parent?.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(getApplication(), R.color.yellow))}
                    2 -> {(parent?.getChildAt(2) as TextView).setTextColor(ContextCompat.getColor(getApplication(), R.color.green))}
                }

        }

    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> {
                Priority.LOW}
        }
    }
}