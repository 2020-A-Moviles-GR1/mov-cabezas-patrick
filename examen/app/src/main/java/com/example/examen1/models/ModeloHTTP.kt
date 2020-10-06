package com.example.examen1.models

import android.util.Log
import com.beust.klaxon.*

class ModeloHTTP (
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    var nombre:String,
    var anio_lanzamiento:Int,
    var precio:Float,
    var disponible:Boolean,//estado
    var latitud:String,
    var longitud:String,
    var url:String,
    var url_img:String,
    val marca: Any? = null
){
//    override fun toString(): String {
//        return "${nombre} | ${anio_lanzamiento} | ${precio} | ${disponible}"
//    }
    override fun toString(): String {
        return "${nombre}"
    }
}

class ParseModelo{
    val conversorModelo = object : Converter{
        override fun canConvert(cls: Class<*>)= cls == ModeloHTTP::class.java

        override fun fromJson(jv: JsonValue): ModeloHTTP {
            if (jv.obj?.get("marca") is Int){
                //para ingresar a las marcas
                return  ModeloHTTP(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombre"),
                    jv.objInt("anio_lanzamiento"),
                    jv.obj?.get("precio").toString().toFloat(),
                    jv.obj?.get("disponible")as Boolean,
                    jv.objString("latitud"),
                    jv.objString("longitud"),
                    jv.objString("url"),
                    jv.objString("url_img"),
                    jv.objInt("marca")
                )
            } else{
                //para ingresar a los modelos
                return ModeloHTTP(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombre"),
                    jv.objInt("anio_lanzamiento"),
                    jv.obj?.get("precio").toString().toFloat(),
                    jv.obj?.get("disponible")as Boolean,
                    jv.objString("latitud"),
                    jv.objString("longitud"),
                    jv.objString("url"),
                    jv.objString("url_img"),
                    Klaxon().parseFromJsonObject<MarcaHTTP>(jv.obj?.get("marca") as JsonObject)
                )
            }
        }

        override fun toJson(value: Any): String {
            return """
                    
                """.trimIndent()
        }
    }
}