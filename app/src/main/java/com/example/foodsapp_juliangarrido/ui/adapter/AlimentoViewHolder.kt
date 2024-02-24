package com.example.foodsapp_juliangarrido.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import com.example.foodsapp_juliangarrido.databinding.ItemAlimentoBinding

class AlimentoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemAlimentoBinding.bind(view)

    fun render(alimento: AlimentoModel, onClickDelete: (Int) -> Unit){
        binding.tvNombre.text = alimento.nombre
        binding.tvKcal.text = alimento.Kcal.toString() + " kcal Total"
        binding.cardView.setOnClickListener { onClickDelete(adapterPosition) }
    }
}