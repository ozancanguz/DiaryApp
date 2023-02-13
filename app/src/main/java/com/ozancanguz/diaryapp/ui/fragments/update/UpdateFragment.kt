package com.ozancanguz.diaryapp.ui.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
       private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


}