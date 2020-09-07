package com.example.examen1

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import java.lang.reflect.Array

class ConexionMarcas {
    val urlPrincipal = "http://192.168.0.9:1337"

    fun obtenerModelo(marcas:ArrayList<Marca>){
        val url = urlPrincipal + "/Marca"
//        var marcas:ArrayList<Marca>
        val responseString = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
//                    Log.i("http-klaxon", "Data: ${data}")

                    val marcas_lista = Klaxon().parseArray<Marca>(data) as ArrayList<Marca>

                    if (marcas_lista != null) {
                        marcas_lista.forEach {
                            marcas.add(it)
                            Log.i(
                                "http-klaxon",
                                "Nombre: ${it.nombre}" + " valor de la empresa: ${it.valor}"
                            )
                        }
                    }

                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
            }

        }
    }


    fun crearMarca(marca:Marca) {
        val url = urlPrincipal + "/marca"

        val parametrosUsuario: List<Pair<String, String >> = listOf(
            "nombre" to marca.nombre,
            "pais_origen" to marca.pais_origen,
            "anio_creacion" to marca.anio_creacion.toString(),
            "sucursal_local" to marca.sucursal_local.toString(),
            "valor" to marca.valor.toString()
        )

        url.httpPost(parametrosUsuario).responseString { req, res, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http-klaxon", "Error: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("http-klaon", "${usuarioString}")
                    Log.i("http-klaon", "Creacion correcta")
                }
            }
        }
    }
    fun eliminaMarca(marca:Marca) {
        val url = urlPrincipal + "/marca"

        val parametrosUsuario: List<Pair<String, String >> = listOf(
            "nombre" to marca.nombre,
            "pais_origen" to marca.pais_origen,
            "anio_creacion" to marca.anio_creacion.toString(),
            "sucursal_local" to marca.sucursal_local.toString(),
            "valor" to marca.valor.toString()
        )

        url.httpDelete(parametrosUsuario).responseString { req, res, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http-klaxon", "Error: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("http-klaon", "${usuarioString}")
                    Log.i("http-klaon", "Eliminacion exitoza")
                }
            }
        }
    }

    fun actualizaMarca(marca:Marca) {
        val url = urlPrincipal + "/marca"

        val parametrosUsuario: List<Pair<String, String >> = listOf(
            "nombre" to marca.nombre,
            "pais_origen" to marca.pais_origen,
            "anio_creacion" to marca.anio_creacion.toString(),
            "sucursal_local" to marca.sucursal_local.toString(),
            "valor" to marca.valor.toString()
        )

        url.httpPut(parametrosUsuario).responseString { req, res, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http-klaxon", "Error: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("http-klaon", "${usuarioString}")
                    Log.i("http-klaon", "Eliminacion exitoza")
                }
            }
        }
    }
}