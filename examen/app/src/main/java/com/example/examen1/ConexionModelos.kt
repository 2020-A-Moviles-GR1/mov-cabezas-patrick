package com.example.examen1

import com.beust.klaxon.Klaxon
import com.example.examen1.models.ModeloHTTP
import com.example.examen1.models.ParseModelo
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ConexionModelos {
    val urlPrincipal = "http://192.168.0.9:1337" + "/modelo"

    fun obtenerModelos(): ArrayList<ModeloHTTP> {
        val url = urlPrincipal;
        var modelos: ArrayList<ModeloHTTP> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                }
                is Result.Success -> {
                    val data = result.get();
                    modelos = ArrayList(
                        Klaxon().converter(ParseModelo().conversorModelo)
                            .parseArray<ModeloHTTP>(data)!!
                    )
                }
            }
        }
        getHttp.join()
        return modelos;
    }

    fun eliminaModelo(id: Int) {
        val url = urlPrincipal + "/$id";
        var cancion: ModeloHTTP? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    cancion = Klaxon().converter(ParseModelo().conversorModelo)
                        .parse<ModeloHTTP>(data)!!
                }
            }
        }
        deleteHttp.join()
    }

    fun crearModelo(parametrosArtista: List<Pair<String, Any>>): ModeloHTTP? {
        var modelo: ModeloHTTP? = null;
        val url = urlPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    modelo = Klaxon().converter(ParseModelo().conversorModelo)
                        .parse<ModeloHTTP>(data)!!
                }
            }
        }
        postHttp.join()
        return modelo
    }

    fun actualizaModelo(parametrosArtista: List<Pair<String, Any>>, id: Number): ModeloHTTP? {
        var modelo: ModeloHTTP? = null;
        val url = urlPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    modelo = Klaxon().converter(ParseModelo().conversorModelo)
                        .parse<ModeloHTTP>(data)!!
                }
            }
        }
        putHttp.join()
        return modelo
    }

}