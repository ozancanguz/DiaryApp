package com.ozancanguz.diaryapp.ui.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
       private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!

    private val args:UpdateFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        // set menu
        setHasOptionsMenu(true)


        updateUi()


        return view



    }

    private fun updateUi() {
        binding.currentTitleEt.setText(args.currentDiary.title)
        binding.currentDescriptionEt.setText(args.currentDiary.description)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}