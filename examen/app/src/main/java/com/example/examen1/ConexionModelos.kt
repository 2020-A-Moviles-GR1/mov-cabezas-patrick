package com.example.examen1

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class ConexionModelos {
    val urlPrincipal = "http://192.168.0.9:1337"

    fun obtenerModelo() {
        val marcaString = """
            {
            "nombre": "m1",
            "anio_lanzamiento": "2020",
            "precio": 300,
            "disponible": true,
            "marca": "LG"
          }
        """.trimIndent()
//        val marcaInstancia = Klaxon().parse<Modelo>(marcaString)

//        if (marcaInstancia != null) {
//            Log.i("http-klaxon", "Nombre: ${marcaInstancia.nombre}")
//            Log.i(
//                "http-klaxon",
//                "Nombre Marca: ${marcaInstancia.marca}"
//            )
//        }

        val url = urlPrincipal + "/modelo"
        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon", "Data: ${data}")

                        val usuarios = Klaxon().parseArray<Modelo>(data)
                        if (usuarios != null) {
                            usuarios.forEach {
                                Log.i("http-klaxon","Nombre: ${it.nombre}"+" valor de la modelo: ${it.precio}")
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


    fun crearModelo() {
        val url = urlPrincipal + "/modelo"

        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "nombre" to "11",
            "anio_lanzamiento" to "2010",
            "precio" to "100",
            "disponible" to "true",
            "marca" to "2"
        )
        url
            .httpPost(parametrosUsuario)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaon", "${usuarioString}")
                    }
                }
            }

    }
}