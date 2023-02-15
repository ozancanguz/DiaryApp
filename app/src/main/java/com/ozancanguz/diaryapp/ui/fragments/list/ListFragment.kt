package com.ozancanguz.diaryapp.ui.fragments.list

import android.os.Bundle
import android.view.*
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
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
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
        super.onCreateOptionsMenu(menu, inflater)
    }

    //swipe to delete
    private fun swipeToDelete(recyclerView: RecyclerView) {

        val swipeToDeleteCallback=object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemtoDelete=listAdapter.todolist[viewHolder.adapterPosition]
                diaryViewModel.deleteSingleItem(itemtoDelete)
                Toast.makeText(requireContext(),"Removed successfully", Toast.LENGTH_LONG).show()


            }
        }
        val itemTouchHelper= ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }



}