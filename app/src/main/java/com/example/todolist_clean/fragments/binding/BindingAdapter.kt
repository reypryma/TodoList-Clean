package com.example.todolist_clean.fragments.binding

import android.graphics.Color
import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.todolist_clean.R
import com.example.todolist_clean.data.models.Priority
import com.example.todolist_clean.data.models.ToDoData
import com.example.todolist_clean.fragments.list.ListFragmentDirections
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

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority) {
             when (priority) {
                Priority.HIGH -> { view.setSelection(0) }
                Priority.MEDIUM -> { view.setSelection(1) }
                Priority.LOW -> { view.setSelection(2) }
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority){
            when(priority){
                Priority.HIGH -> { cardView.setCardBackgroundColor(Color.parseColor(R.color.red.toString())) }
                Priority.MEDIUM -> { cardView.setCardBackgroundColor(Color.parseColor(R.color.yellow.toString())) }
                Priority.LOW -> { cardView.setCardBackgroundColor(Color.parseColor(R.color.green.toString())) }
            }
        }

        @BindingAdapter("android:sendDataToUpdate")
        @JvmStatic
        fun sendDataToUpdate(view: ConstraintLayout, currentItem : ToDoData): Unit {
               view.setOnClickListener {
                   val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                   view.findNavController().navigate(action)
               }
        }
    }
}

