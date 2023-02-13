package com.ozancanguz.diaryapp.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        val updatedItem=Diary(args.currentDiary.id,title,description)

// data updated in fragment list
        viewmodel.updateData(updatedItem)
        Toast.makeText(requireContext(),"item updated", Toast.LENGTH_LONG).show()

        // navigate to fragment list
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)



    }

    private fun getArgs() {
        binding.currentTitleEt.setText(args.currentDiary.title)
        binding.currentDescriptionEt.setText(args.currentDiary.description)
    }
    private fun deleteSingleItem() {

        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewmodel.deleteSingleItem(args.currentDiary)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentDiary.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentDiary.title}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentDiary.title}'?")
        builder.create().show()

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.menu_save){
            updateItem()
        }else if(item.itemId==R.id.menu_delete){
            deleteSingleItem()
        }
        return super.onOptionsItemSelected(item)
    }



}