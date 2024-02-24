package com.example.foodsapp_juliangarrido.data.model

import java.io.Serializable

data class AlimentoModel (val nombre: String,
                     val tipo:String = "simple",
                     var grHC: Double=0.0,
                     var grLip:Double=0.0,
                     var grPro:Double=0.0): Serializable {

    var Kcal:Double=0.0

    var ingredientes :MutableList<IngredienteModel?> = mutableListOf(null)

    init{
        calculaKcal()
    }

    private fun calculaKcal() {
        this.Kcal=(4*grHC+4*grPro+9*grLip)
    }

    private fun calculaCantidades(ing: IngredienteModel){

        this.grHC+=ing.alimento.grHC*ing.cantidad/100
        this.grLip+=ing.alimento.grLip*ing.cantidad/100
        this.grPro+=ing.alimento.grPro*ing.cantidad/100

    }

    fun addIngrediente(ing: IngredienteModel){
        ingredientes.add(ing)
        this.calculaCantidades(ing)
        this.calculaKcal()
    }

    fun recalcula(){
        this.grHC=0.0
        this.grLip=0.0
        this.grPro=0.0
        for(ing in ingredientes){
            if (ing!=null) {
                this.calculaCantidades(ing)
                this.calculaKcal()
            }
        }
    }



}