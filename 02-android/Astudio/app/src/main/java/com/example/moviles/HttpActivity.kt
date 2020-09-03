package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
    var urlPrincipal = "http://192.168.0.9:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener.setOnClickListener({
            obtenerUsuarios()
        })
        btn_crear.setOnClickListener({
            crearUsuario()
        })
    }
    fun crearUsuario(){
        var url = urlPrincipal +"/Usuario"

        val parametrosUsuarios= listOf(
            "cedula" to "112123433",
            "nombre" to "ramiro",
            "correo" to "r@r.com",
            "estadoCivil" to "casado",
            "password" to "apaswordSeguro101"
        )

        url
            .httpPost(parametrosUsuarios)
            .responseString{
                req,res,result->
                when (result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","Error: ${error}")
                    }
                    is Result.Success->{
                        val usuarioString=result.get()
                        Log.i("http-klaxon","${usuarioString}")
                    }
                }
            }
    }

    fun obtenerUsuarios(){
//        var pokemonString = """
//       {
//        "createdAt": 1597926659208,
//        "updatedAt": 1597926659208,
//        "id": 1,
//        "nombre": "carlos",
//        "usuario": "1"
//      }
//        """.trimIndent()
//        val pokemonInstancia =Klaxon()
//            .parse<PokemonHttp>(pokemonString)
//
//        if(pokemonInstancia != null){
//            Log.i("http-klaxon","Nombre: ${pokemonInstancia.nombre}")
//            Log.i("http-klaxon","Nombre: ${pokemonInstancia.fechaCreacin}")
//        }

        val url = urlPrincipal + "/Usuario"
        url
            .httpGet()
            .responseString {
                    request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
//                        Log.i("http-klaxon","Data: ${data}")

                        val usuarios=Klaxon()
                            .parseArray<UsuarioHttp>(data)
                        if(usuarios != null){
                            usuarios.forEach{
                                Log.i("http-klaxon","nombre: ${it.nombre} + estado civil: ${it.estadoCivil}")
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
}