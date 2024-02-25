package com.example.foodsapp_juliangarrido.ui.fragments.simple

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
import com.example.foodsapp_juliangarrido.R
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import com.example.foodsapp_juliangarrido.databinding.FragmentSimpleBinding
import com.example.foodsapp_juliangarrido.ui.adapter.Alimentos.AlimentoAdapter
import com.example.foodsapp_juliangarrido.ui.viewmodels.SharedViewModel


class SimpleFragment : Fragment() {

    private var _binding: FragmentSimpleBinding? = null
    private val binding get() = _binding!!
    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var adapterAlimento: AlimentoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Inicializamos los listeners
        initListener()

        sharedViewModel.alimentoMutableList.observe(viewLifecycleOwner) {

            adapterAlimento = AlimentoAdapter(
                sharedViewModel.obtenerAlimentos("simple"),
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })

            binding.rvSimple.layoutManager =
                LinearLayoutManager(context)
            binding.rvSimple.adapter = adapterAlimento
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
        dialog!!.setContentView(R.layout.add_dialog)

        val etNombre: EditText = dialog.findViewById(R.id.etDialog)
        val etGrHC: EditText = dialog.findViewById(R.id.etGrHC)
        val etGrLip: EditText = dialog.findViewById(R.id.etGrLip)
        val etGrPro: EditText = dialog.findViewById(R.id.etGrPro)
        val btnDialog: Button = dialog.findViewById(R.id.btnAddAlimento)

        btnDialog.setOnClickListener {
                val nombre = etNombre.text.toString()
                val grHC = etGrHC.text.toString().toDoubleOrNull() ?: 0.0
                val grLip = etGrLip.text.toString().toDoubleOrNull() ?: 0.0
                val grPro = etGrPro.text.toString().toDoubleOrNull() ?: 0.0
                //Almacenamos los datos para crear un nuevo alimento
                sharedViewModel.agregarAlimento(AlimentoModel(nombre, "simple", grHC, grLip, grPro))
            dialog.dismiss()
        }
        dialog.show()
    }




}