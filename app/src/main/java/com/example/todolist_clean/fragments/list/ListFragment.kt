package com.example.todolist_clean.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_clean.R
import com.example.todolist_clean.SwipeToDelete
import com.example.todolist_clean.ToDoViewModel
import com.example.todolist_clean.data.models.ToDoData
import com.example.todolist_clean.databinding.FragmentListBinding
import com.example.todolist_clean.fragments.SharedViewModel
import com.example.todolist_clean.fragments.list.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {
    private val adapter: ListAdapter by lazy { ListAdapter() }

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel
        /*        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recycleView = view.recycleView
        recycleView.adapter = adapter;
        recycleView.layoutManager = LinearLayoutManager(requireActivity())*/

        setupRecyclerView()

        //Observe live data
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data) //Observer giving true if emptyDatabase true
            adapter.setData(data)
        })


        //Show menu
        setHasOptionsMenu(true)
        return binding.root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //remove memory leaks
        _binding = null
    }

    private fun setupRecyclerView() {
        val recycleView = binding.recycleView
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireActivity())
        swipeToDelete(recycleView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.dataList[viewHolder.adapterPosition]
                //Delete Item
                mToDoViewModel.deleteItem(itemToDelete)
                Toast.makeText(requireContext(), "Successfully deleted: ${itemToDelete.title}", Toast.LENGTH_SHORT).show()

                //Restore Deleted Item
                restoreDeletedData(viewHolder.itemView, itemToDelete, viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    
    private fun restoreDeletedData(view: View, deletedItem: ToDoData, position: Int): Unit {
        val snackbar = Snackbar.make(
            view, "Deleted '${deletedItem.title}'",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo"){
            mToDoViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }.show()
    }

    //Create menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all){
               confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully deleted everything",
                Toast.LENGTH_SHORT).show()
        }
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        builder.setNegativeButton("No"){
                _, _ ->
        }
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure want to remove everything?")
        builder.create().show()
    }
}





