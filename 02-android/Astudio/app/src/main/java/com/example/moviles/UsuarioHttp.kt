package com.example.moviles

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*

class UsuarioHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var cedula: String,
    var nombre: String,
    var correo: String,
    var estadoCivil: String,
    var password: String,
    var pokemons: ArrayList<PokemonHttp>? = null
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}

class ParseUsuario{
    val conversorUsuario = object: Converter {
        override fun canConvert(cls: Class<*>)= cls == UsuarioHttp::class.java

        override fun fromJson(jv: JsonValue): UsuarioHttp {

            return UsuarioHttp(
                jv.objInt("id"),
                jv.objInt("createdAt").toLong(),
                jv.objInt("updatedAt").toLong(),
                jv.objString("cedula"),
                jv.objString("nombre"),
                jv.objString("correo"),
                jv.objString("estadoCivil"),
                jv.objString("password"),
                Klaxon().converter(ParsePokemon().conversorModelo)
                    .parseFromJsonArray<PokemonHttp>(
                        jv.obj?.get("pokemons") as JsonArray<*>
                    ) as ArrayList<PokemonHttp>
            )
        }

        override fun toJson(value: Any): String {
            return """
                    
                """.trimIndent()
        }
    }
}
