package com.ozancanguz.diaryapp.ui.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.data.model.Diary
import com.ozancanguz.diaryapp.databinding.FragmentUpdateBinding
import com.ozancanguz.diaryapp.viewmodel.DiaryViewModel


class UpdateFragment : Fragment() {
       private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!

    private val args:UpdateFragmentArgs by navArgs()

    //init viewmodel
    private val viewmodel:DiaryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        // set menu
        setHasOptionsMenu(true)


        getArgs()


        return view




    }

    private fun updateItem() {
        val title=binding.currentTitleEt.text.toString()
        val description=binding.currentDescriptionEt.text.toString()

        val updatedItem=Diary(title,description)



    }

    private fun getArgs() {
        binding.currentTitleEt.setText(args.currentDiary.title)
        binding.currentDescriptionEt.setText(args.currentDiary.description)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.menu_save){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

}