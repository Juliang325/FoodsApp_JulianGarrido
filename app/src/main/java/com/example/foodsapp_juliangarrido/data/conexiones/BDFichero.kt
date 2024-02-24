package com.example.foodsapp_juliangarrido.data.conexiones

import android.content.Context
import com.example.foodsapp_juliangarrido.data.model.AlimentoModel
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class BDFichero(private val context: Context){
    val nombre="alimentos.dat"
    fun escribir(lista:MutableList<AlimentoModel>,nombreArchivo: String=nombre) {
        try {
            val fileOutputStream = context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(lista)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun leer():MutableList<AlimentoModel> {
        var lista: MutableList<AlimentoModel>? = null
        try {
            val fileInputStream = context.openFileInput(nombre)
            val objectInputStream = ObjectInputStream(fileInputStream)
            lista = objectInputStream.readObject() as MutableList<AlimentoModel>
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return lista ?: mutableListOf()
    }
    fun borrarArchivos() {
        val archivo = context.getFileStreamPath(nombre)
        if (archivo.exists()) {
            archivo.delete()
        }
    }
}