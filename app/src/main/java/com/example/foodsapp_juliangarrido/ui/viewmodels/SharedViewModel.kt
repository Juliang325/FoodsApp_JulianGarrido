package com.example.foodsapp_juliangarrido.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.foodsapp_juliangarrido.data.daos.AlimentoDao
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var alimentoDao = AlimentoDao(application)

    var alimentoMutableList = MutableLiveData<MutableList<AlimentoModel>>()

    init{
        alimentoMutableList.value = alimentoDao.getAlimentos()
    }

    fun obtenerAlimentos(tipo: String): MutableList<AlimentoModel> {
        val alimentos = alimentoMutableList.value ?: mutableListOf()
        return when (tipo) {
            "simple" -> alimentos.filter { it.tipo == "simple" }.toMutableList()
            "receta" -> alimentos.filter { it.tipo == "receta" }.toMutableList()
            "menu" -> alimentos.filter { it.tipo == "menu" }.toMutableList()
            "dieta" -> alimentos.filter { it.tipo == "dieta" }.toMutableList()
            else -> alimentos
        }
    }

    fun agregarAlimento(alimento: AlimentoModel) {
        alimentoDao.addAlimento(alimento)
        alimentoMutableList.value?.add(alimento)
        alimentoMutableList.postValue(alimentoMutableList.value)
    }

    fun eliminarAlimento(alimento:AlimentoModel) {
        alimentoDao.deleteAlimento(alimento)
        alimentoMutableList.value?.remove(alimento)
        alimentoMutableList.postValue(alimentoMutableList.value)
    }

    fun deleteAlimentoAtPosition(posicion:Int) {
        alimentoDao.deleteAlimentoAtPosition(posicion)
        alimentoMutableList.value?.removeAt(posicion)
        alimentoMutableList.postValue(alimentoMutableList.value)
    }

    fun actualizarAlimento(alimento: AlimentoModel) {
        alimentoDao.updateAlimento(alimento)
        val index = alimentoMutableList.value?.indexOfFirst { it.nombre == alimento.nombre }
        if (index != null && index != -1) {
            alimentoMutableList.value?.set(index, alimento)
            alimentoMutableList.postValue(alimentoMutableList.value)
        }
    }
}