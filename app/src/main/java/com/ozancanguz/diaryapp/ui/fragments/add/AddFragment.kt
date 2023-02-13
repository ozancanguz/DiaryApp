package com.ozancanguz.diaryapp.ui.fragments.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.data.model.Diary
import com.ozancanguz.diaryapp.databinding.FragmentAddBinding
import com.ozancanguz.diaryapp.viewmodel.DiaryViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class AddFragment : Fragment() {
      private var _binding: FragmentAddBinding? = null

    private val binding get() = _binding!!

    private val diaryViewModel:DiaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root


        // set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add){
          insertToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    fun insertToDb(){
        val title=binding.titleEt.text.toString()
        val description=binding.descriptionEt.text.toString()
        var newData= Diary(0,title,description)
        diaryViewModel.insertData(newData)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }


}