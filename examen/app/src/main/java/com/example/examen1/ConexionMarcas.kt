package com.example.examen1

import com.beust.klaxon.Klaxon
import com.example.examen1.models.MarcaHTTP
import com.example.examen1.models.ModeloHTTP
import com.example.examen1.models.ParseMarcas
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ConexionMarcas {
    val urlPrincipal = "http://192.168.0.9:1337" + "/Marca"

    fun obtenerMarcas() : ArrayList<MarcaHTTP> {
        val url = urlPrincipal;

        var marcas: ArrayList<MarcaHTTP> = arrayListOf()

        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marcas = ArrayList(
                        Klaxon().converter(ParseMarcas().conversorMarca)
                            .parseArray<MarcaHTTP>(data)!!
                    )
                }
            }
        }
        getHttp.join()
        return marcas;
    }

    fun obtenerMarca(id: Int): MarcaHTTP?{
        val url = urlPrincipal + "/$id";
        var marca: MarcaHTTP? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marca = Klaxon().converter(ParseMarcas().conversorMarca)
                        .parse<MarcaHTTP>(data)!!
                }
            }
        }
        getHttp.join()
        return marca
    }

    fun encuentraMarca(nombre:String):MarcaHTTP?{
        val url = urlPrincipal;

        var marcas: ArrayList<MarcaHTTP> = arrayListOf()

        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marcas = ArrayList(
                        Klaxon().converter(ParseMarcas().conversorMarca)
                            .parseArray<MarcaHTTP>(data)!!
                    )
                }
            }
        }
        getHttp.join()
        var marca = marcas.find { it: MarcaHTTP ->
            it.nombre == nombre }
        return marca
    }

    fun eliminaMarca(id: Int): MarcaHTTP?{
        val url = urlPrincipal + "/$id";
        var marca: MarcaHTTP? = null;

        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marca = Klaxon().converter(ParseMarcas().conversorMarca)
                        .parse<MarcaHTTP>(data)!!
                }
            }
        }
        deleteHttp.join()
        return marca
    }

    fun eliminaModelos(marca:MarcaHTTP){
        var con = ConexionModelos()

        marca.modelos?.forEach {
                con.eliminaModelo(it.id)
        }
    }

    fun crearMarca(marcaDatos: List<Pair<String, Any>>): MarcaHTTP?{
        var marca: MarcaHTTP? = null;
        val url = urlPrincipal
        val postHttp = url.httpPost(marcaDatos).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marca = Klaxon().converter(ParseMarcas().conversorMarca)
                        .parse<MarcaHTTP>(data)!!
                }
            }
        }
        postHttp.join()
        return marca
    }

    fun actualizaMarca(parametrosArtista: List<Pair<String, Any>>, id: Number): MarcaHTTP?{
        var marca: MarcaHTTP? = null;
        val url = urlPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get();
                    marca = Klaxon().converter(ParseMarcas().conversorMarca)
                        .parse<MarcaHTTP>(data)!!
                }
            }
        }
        putHttp.join()
        return marca
    }
}