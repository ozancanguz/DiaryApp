package com.ozancanguz.diaryapp.ui.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_list.*


class AddFragment : Fragment() {
      private var _binding: FragmentAddBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }


}