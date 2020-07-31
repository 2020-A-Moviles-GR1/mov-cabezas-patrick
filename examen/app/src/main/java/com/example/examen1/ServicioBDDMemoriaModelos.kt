package com.example.examen1

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class ServicioBDDMemoriaModelos {
    companion object {

        val listaModelos = arrayListOf<Modelo>()

        fun agragaModelo(modelo: Modelo) {
            val marcas = ServicioBDDMemoriaMarcas.listaMarcas

            val esta = marcas.find { it: Marca ->
                it.nombre == modelo.marca
            }
            if (esta != null) {
                listaModelos.add(modelo)
            } else {
                Log.i("Error", "No existe la marca")
            }
        }

        fun eliminarModelo(posiicon: Int) {
            listaModelos.remove(listaModelos.get(posiicon))
        }
    }
}

