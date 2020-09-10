package com.example.moviles

import com.beust.klaxon.*
import java.util.*

class PokemonHttp(
    val createdAt: Long,
    val updatedAt: Long,
    var id: Int,
    var nombre: String,
    var usuario: Any? = null
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}

class ParsePokemon{
    val conversorModelo = object : Converter{
        override fun canConvert(cls: Class<*>)= cls == PokemonHttp::class.java

        override fun fromJson(jv: JsonValue): PokemonHttp {
            if (jv.obj?.get("usuario") is Int){
                return  PokemonHttp(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombre"),
                    jv.objInt("usuario")
                )
            } else{
                return PokemonHttp(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("nombre"),
                    Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("usuario") as JsonObject)
                )
            }
        }

        override fun toJson(value: Any): String {
            return """
                    
                """.trimIndent()
        }

    }
}

