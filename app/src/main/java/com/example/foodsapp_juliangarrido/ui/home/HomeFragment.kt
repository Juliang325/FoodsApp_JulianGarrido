package com.example.foodsapp_juliangarrido.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.foodsapp_juliangarrido.databinding.FragmentHomeBinding
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import com.example.foodsapp_juliangarrido.ui.viewmodels.SharedViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Añadir un nuevo alimento
//        val nuevoAlimento = AlimentoModel("maiz", "simple", 10.0, 5.0, 20.0)
//        sharedViewModel.agregarAlimento(nuevoAlimento)

        val textView: TextView = binding.textHome
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