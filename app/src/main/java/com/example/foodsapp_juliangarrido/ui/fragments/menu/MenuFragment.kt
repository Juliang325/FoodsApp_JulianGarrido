package com.example.foodsapp_juliangarrido.ui.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.foodsapp_juliangarrido.R
import com.example.foodsapp_juliangarrido.databinding.FragmentDietaBinding
import com.example.foodsapp_juliangarrido.databinding.FragmentMenuBinding
import com.example.foodsapp_juliangarrido.ui.viewmodels.SharedViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


}