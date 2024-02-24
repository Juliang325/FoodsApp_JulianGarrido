package com.example.foodsapp_juliangarrido.data.daos

import android.content.Context
import com.example.foodsapp_juliangarrido.data.conexiones.BDFichero
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel

open class AlimentoDao(private val context: Context) {

    private val bdFicheroAlimento = BDFichero(context)


    fun addAlimento(al: AlimentoModel) {
        val lista=bdFicheroAlimento.leer()
        lista.add(al)
        bdFicheroAlimento.escribir(lista)
    }
    fun getAlimentos(): MutableList<AlimentoModel> {
        return bdFicheroAlimento.leer()
    }
    fun getAlimentos(tipo: String): MutableList<AlimentoModel> {
        val lista=bdFicheroAlimento.leer()
        return lista.filter{it.tipo==tipo} as MutableList<AlimentoModel>
    }
    fun getAlimento(al: AlimentoModel): AlimentoModel {
        val lista = bdFicheroAlimento.leer()
        return lista.firstOrNull { it == al } ?: throw NoSuchElementException("El alimento no se encuentra en la base de datos")
    }

    fun updateAlimento(al: AlimentoModel) {
        val lista = bdFicheroAlimento.leer()
        val index = lista.indexOfFirst { it == al }
        if (index != -1) {
            lista[index] = al
            bdFicheroAlimento.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

    fun deleteAlimento(al: AlimentoModel) {
        val lista = bdFicheroAlimento.leer()
        if (lista.remove(al)) {
            bdFicheroAlimento.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

    fun deleteAlimentoAtPosition(posicion: Int) {
        val lista = bdFicheroAlimento.leer()
        if (posicion in 0 until lista.size) {
            lista.removeAt(posicion)
            bdFicheroAlimento.escribir(lista)
        } else {
            throw IndexOutOfBoundsException("La posición está fuera del rango de la lista de alimentos")
        }
    }

}