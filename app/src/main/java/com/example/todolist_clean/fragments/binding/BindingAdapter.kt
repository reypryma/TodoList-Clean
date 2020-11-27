package com.example.todolist_clean.fragments.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.todolist_clean.R
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
        @BindingAdapter("android:emtpyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false-> view.visibility = View.INVISIBLE
            }
        }
    }
}