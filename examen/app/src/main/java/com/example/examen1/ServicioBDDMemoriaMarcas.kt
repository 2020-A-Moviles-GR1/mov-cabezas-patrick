package com.example.examen1

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlin.math.log

class ServicioBDDMemoriaMarcas (){

    companion object{
        val listaMarcas = arrayListOf<Marca>()

        fun agragaMarca(marca:Marca){
            listaMarcas.add(marca)
        }
        fun eliminarMarca(posiicon:Int){
            listaMarcas.remove(listaMarcas.get(posiicon))
        }
    }
}
