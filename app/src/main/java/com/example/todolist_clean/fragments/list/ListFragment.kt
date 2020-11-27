package com.example.todolist_clean.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist_clean.R
import com.example.todolist_clean.ToDoViewModel
import com.example.todolist_clean.databinding.FragmentListBinding
import com.example.todolist_clean.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

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

        /*
        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, {
            showEmptyDatabaseView(it) //get observer it from getAllData
        })
*/

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
    }

    /*private fun showEmptyDatabaseView(emptyDatabase: Boolean) {
        if(emptyDatabase){
            view?.no_data_imageView?.visibility = View.VISIBLE
            view?.no_data_textView?.visibility = View.VISIBLE
        }else{
            view?.no_data_imageView?.visibility = View.INVISIBLE
            view?.no_data_textView?.visibility = View.INVISIBLE
        }
    }*/

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





