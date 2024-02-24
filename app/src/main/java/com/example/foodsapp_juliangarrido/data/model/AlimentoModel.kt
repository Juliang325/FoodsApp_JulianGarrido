package com.example.foodsapp_juliangarrido.data.model

import java.io.Serializable

data class AlimentoModel (val nombre: String,
                     val tipo:String = "simple",
                     var grHC: Double=0.0,
                     var grLip:Double=0.0,
                     var grPro:Double=0.0): Serializable {
    var Kcal: Double = 0.0
    var ingredientes: MutableList<AlimentoModel> = mutableListOf()  // Lista de ingredientes

    init {
        calculaKcal()
    }

    private fun calculaKcal() {
        if (tipo == "simple") {
            this.Kcal = (4 * grHC + 4 * grPro + 9 * grLip)
        } else if (tipo == "receta") {
            this.Kcal = ingredientes.sumByDouble { it.Kcal }
        }
    }

    fun agregarIngrediente(ingrediente: AlimentoModel) {
        if (tipo == "receta") {
            ingredientes.add(ingrediente)
            calculaKcal()
        }
    }

    fun eliminarIngrediente(ingrediente: AlimentoModel) {
        if (tipo == "receta") {
            ingredientes.remove(ingrediente)
            calculaKcal()
        }
    }



}