package com.example.foodsapp_juliangarrido.ui.adapter.Alimentos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp_juliangarrido.R
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel

class AlimentoAdapter(
    private val alimentos: List<AlimentoModel>,
    private val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<AlimentoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlimentoViewHolder(layoutInflater.inflate(R.layout.item_alimento, parent, false))
    }


    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val item = alimentos[position]
        holder.render(item, onClickDelete)
    }


    override fun getItemCount(): Int = alimentos.size
}