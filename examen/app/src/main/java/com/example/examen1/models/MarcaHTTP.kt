package com.example.examen1.models

import android.util.Log
import com.beust.klaxon.*

class MarcaHTTP(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val nombre:String,
    val pais_origen:String,
    val anio_creacion:Int,
    val sucursal_local:Boolean,
    val valor:Float,
    var modelos: ArrayList<ModeloHTTP>? = null
) {
//    override fun toString(): String {
//        return "${nombre} | ${pais_origen} | ${anio_creacion} | ${sucursal_local} | ${valor}"
//    }
    override fun toString(): String {
        return "${nombre}"
    }
}

class ParseMarcas{
    val conversorMarca = object: Converter{
        override fun canConvert(cls: Class<*>)= cls == MarcaHTTP::class.java

        override fun fromJson(jv: JsonValue): MarcaHTTP {
            return MarcaHTTP(
                jv.objInt("createdAt").toLong(),
                jv.objInt("updatedAt").toLong(),
                jv.objInt("id"),
                jv.objString("nombre"),
                jv.objString("pais_origen"),
                jv.objInt("anio_creacion"),
                jv.obj?.get("sucursal_local") as Boolean,
                jv.obj?.get("valor").toString().toFloat(),
                Klaxon().converter(ParseModelo().conversorModelo)
                    .parseFromJsonArray<ModeloHTTP>(
                        jv.obj?.get("modelo") as JsonArray<*>
                    ) as ArrayList<ModeloHTTP>
            )
        }

        override fun toJson(value: Any): String {
            return """
                    
                """.trimIndent()
        }
    }
}