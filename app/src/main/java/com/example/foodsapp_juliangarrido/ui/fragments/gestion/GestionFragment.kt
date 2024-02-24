package com.example.foodsapp_juliangarrido.ui.fragments.gestion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.foodsapp_juliangarrido.databinding.FragmentGestionBinding
import com.example.foodsapp_juliangarrido.ui.viewmodels.SharedViewModel

class GestionFragment : Fragment() {

    private var _binding: FragmentGestionBinding? = null
    private val binding get() = _binding!!
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGestionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Añadir un nuevo alimento
//        val nuevoAlimento = AlimentoModel("arepita", "simple", 10.0, 5.0, 20.0)
//        sharedViewModel.agregarAlimento(nuevoAlimento)

        sharedViewModel.alimentoMutableList.observe(viewLifecycleOwner) {

            sharedViewModel.obtenerAlimentos().forEach { alimento ->
                Log.d("Alimento", alimento.toString())
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}