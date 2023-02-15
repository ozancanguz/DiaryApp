package com.ozancanguz.diaryapp.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.data.adapter.ListAdapter
import com.ozancanguz.diaryapp.databinding.FragmentListBinding
import com.ozancanguz.diaryapp.util.SwipeToDelete
import com.ozancanguz.diaryapp.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.removed_toast.*

@AndroidEntryPoint

class ListFragment : Fragment(),SearchView.OnQueryTextListener{
       private var _binding: FragmentListBinding? = null
       private val binding get() = _binding!!

    private val diaryViewModel:DiaryViewModel by viewModels()

    private var listAdapter=ListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // setMenu
        setHasOptionsMenu(true)

      binding.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }

        // set up Rv
        setupRv()

        // observe live data and update ui
        observeLiveData()

        return view
    }



    private fun observeLiveData() {

        diaryViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            listAdapter.updateData(it)
        })
    }

    private fun setupRv() {
         binding.recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter=listAdapter
        swipeToDelete(binding.recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.listfragment_menu,menu)
        // search query applied
        val search=menu.findItem(R.id.menu_search)
        val searchView=search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled=true
        searchView?.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //swipe to delete
    private fun swipeToDelete(recyclerView: RecyclerView) {

        val swipeToDeleteCallback=object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemtoDelete=listAdapter.todolist[viewHolder.adapterPosition]
                diaryViewModel.deleteSingleItem(itemtoDelete)
                Toast(requireContext()).apply {
                    duration=Toast.LENGTH_LONG
                    setGravity(Gravity.CENTER,0,0)
                    view=layoutInflater.inflate(R.layout.removed_toast,removedToast)
                }.show()


            }
        }
        val itemTouchHelper= ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    // search database
    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        diaryViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner) { list ->
            list?.let {
                Log.d("ListFragment", "searchThroughDatabase")
                listAdapter.updateData(it)
            }
        }
    }


    // for search database
    override fun onQueryTextSubmit(query: String?): Boolean {

        if(query!=null){
            searchThroughDatabase(query)
        }
        return true
    }



    override fun onQueryTextChange(query: String?): Boolean {
        if(query!=null){
            searchThroughDatabase(query)
        }
        return true
    }


}