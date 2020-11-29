package com.example.todolist_clean.fragments.binding

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.todolist_clean.R
import com.example.todolist_clean.data.models.Priority
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
            }
        }
        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false-> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriority")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority) {
             when (priority) {
                Priority.HIGH -> { view.setSelection(0) }
                Priority.MEDIUM -> { view.setSelection(1) }
                Priority.LOW -> { view.setSelection(2) }
            }
        }
    }
}