package com.example.foodsapp_juliangarrido.data.model

class AlimentoProvider {
    companion object{
        val alimentos = listOf(
            AlimentoModel("zanahoria", "simple", 0.0, 0.0, 0.0),
            AlimentoModel("manzana", "simple", 0.3, 21.0, 0.2),
            AlimentoModel("plátano", "simple", 1.3, 23.0, 0.4),
            AlimentoModel("lechuga", "simple", 1.4, 2.9, 0.2),
            AlimentoModel("sandía", "simple", 0.6, 8.1, 0.2),
            AlimentoModel("pera", "simple", 0.4, 15.5, 0.1)

        )
    }
}