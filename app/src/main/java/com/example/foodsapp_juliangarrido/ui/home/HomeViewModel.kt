package com.example.foodsapp_juliangarrido.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsapp_juliangarrido.data.daos.AlimentoDao
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import com.example.foodsapp_juliangarrido.data.model.AlimentoProvider

class HomeViewModel(private val alimentoDao: AlimentoDao) : ViewModel() {

    val alimentoModel = MutableLiveData<MutableList<AlimentoModel>>()

    init {
        alimentoModel.value = alimentoDao.getAlimentos().toMutableList()
    }

    fun obtenerAlimentos(): MutableList<AlimentoModel> {
        return alimentoModel.value ?: mutableListOf()
    }

    fun agregarAlimento(alimento: AlimentoModel) {
        alimentoDao.addAlimento(alimento)
        alimentoModel.value?.add(alimento)
        alimentoModel.postValue(alimentoModel.value)
    }

    fun eliminarAlimento(alimento: AlimentoModel) {
        alimentoDao.deleteAlimento(alimento)
        alimentoModel.value?.remove(alimento)
        alimentoModel.postValue(alimentoModel.value)
    }

    fun actualizarAlimento(alimento: AlimentoModel) {
        alimentoDao.updateAlimento(alimento)
        val index = alimentoModel.value?.indexOfFirst { it.nombre == alimento.nombre }
        if (index != null && index != -1) {
            alimentoModel.value?.set(index, alimento)
            alimentoModel.postValue(alimentoModel.value)
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}