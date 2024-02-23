package com.example.foodsapp_juliangarrido.ui.gestion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodsapp_juliangarrido.R

class GestionFragment : Fragment() {

    companion object {
        fun newInstance() = GestionFragment()
    }

    private lateinit var viewModel: GestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}