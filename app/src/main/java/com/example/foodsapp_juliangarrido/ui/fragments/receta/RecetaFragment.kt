package com.example.foodsapp_juliangarrido.ui.fragments.receta

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp_juliangarrido.R
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import com.example.foodsapp_juliangarrido.databinding.FragmentRecetaBinding
import com.example.foodsapp_juliangarrido.ui.adapter.Alimentos.AlimentoAdapter
import com.example.foodsapp_juliangarrido.ui.viewmodels.SharedViewModel

class RecetaFragment : Fragment() {
    private var _binding: FragmentRecetaBinding? = null
    private val binding get() = _binding!!
    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var adapterAlimento: AlimentoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecetaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        sharedViewModel.alimentoMutableList.observe(viewLifecycleOwner) {
            initListener()
            adapterAlimento = AlimentoAdapter(
                sharedViewModel.obtenerAlimentos("receta"),
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })

            binding.rvReceta.layoutManager =
                LinearLayoutManager(context)
            binding.rvReceta.adapter = adapterAlimento
        }

        return root
    }

    /********  BORRAR ALIMENTO   **********/
    //Mostrar alertDialogo para confirmar el borrado de alimento
    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmación")
        builder.setMessage("¿Estás seguro de que quieres borrarlo?")

        builder.setPositiveButton("Sí") { _, _ ->
            onClickDelete(position)
        }

        builder.setNegativeButton("No") { _, _ ->
        }

        val dialog = builder.create()
        dialog.show()
    }

    //Logica para borrar elemento de la lista
    private fun onClickDelete(position: Int) {
        if (position >= 0 && position < (sharedViewModel.alimentoMutableList.value?.size ?: 0)) {
            sharedViewModel.deleteAlimentoAtPosition(position)
        }

    }

    /****** AÑADIR ELEMENTO *****/
    private fun initListener(){
        binding.btnAdd.setOnClickListener { showDialog() }
    }
    private fun showDialog() {
        val dialog = context?.let { Dialog(it) }
        dialog!!.setContentView(R.layout.add_receta)

        val recyclerViewAlimentos = dialog.findViewById<RecyclerView>(R.id.rvAlimentos)
        val adaptadorAlimento = AlimentoAdapter(
            sharedViewModel.obtenerAlimentos("simple"),
            onClickDelete = { position -> showDeleteConfirmationDialog(position) })
        recyclerViewAlimentos.adapter = adaptadorAlimento
        recyclerViewAlimentos.layoutManager = LinearLayoutManager(requireContext())


        val etNombre: EditText = dialog.findViewById(R.id.etDialog)
        val btnDialog: Button = dialog.findViewById(R.id.btnAddReceta)

        btnDialog.setOnClickListener {
            val nombre = etNombre.text.toString()
            //Almacenamos los datos para crear un nuevo alimento
            sharedViewModel.agregarAlimento(AlimentoModel(nombre, "receta"))
            dialog.dismiss()
        }
        dialog.show()
    }
}