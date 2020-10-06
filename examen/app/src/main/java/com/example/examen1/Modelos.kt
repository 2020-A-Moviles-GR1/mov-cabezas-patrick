package com.example.examen1

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.examen1.models.ModeloHTTP
import kotlinx.android.synthetic.main.activity_modelos.*

class Modelos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelos)

        var posicion = 0
        var idModelo = 0
        var con = ConexionModelos()
        val listaModelos = con.obtenerModelos()

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaModelos
        )
        lv_modelos.adapter=adaptador
        lv_modelos.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id->
            posicion = position
            idModelo = llenarCampos(listaModelos.get(position))
        }

        btn_agregar_m.setOnClickListener({
            var datos = parseModelos(getElementos())
            if(datos != null){
                var modelo = con.crearModelo(datos)
                if(modelo != null){
                    listaModelos.add(modelo)
                }
                adaptador.notifyDataSetChanged()
                limpiarCampos()
            }
        })

        btn_actualizar_m.setOnClickListener({
            var datos = parseModelos(getElementos())
            if(datos != null){
                var modelo = con.actualizaModelo(datos,idModelo)
                if(modelo != null){
                    listaModelos.removeAt(posicion)
                    listaModelos.add(modelo)
                }
                adaptador.notifyDataSetChanged()
                limpiarCampos()
            }
        })
        btn_eliminar_m.setOnClickListener({
            var modelo = con.eliminaModelo(idModelo)
            if(modelo != null)
                listaModelos.removeAt(posicion)
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_limpiar_m.setOnClickListener({
            limpiarCampos()
        })
    }

    fun getElementos():Modelo{
        var nombre: String = txt_nombre_m.getText().toString()
        var anio : Int = txt_anio_m.getText().toString().toInt()
        var precio : Float= txt_precio_m.getText().toString().toFloat()
        var nombre_marca : String = txt_nomre_marca.getText().toString()
        var disponible : Boolean = ch_disponible.isChecked
        var latitud: String = txt_latitud.getText().toString()
        var longitud: String = txt_longitud.getText().toString()
        var url: String = txt_url.getText().toString()
        var url_img: String = txt_url_img.getText().toString()
        return Modelo(nombre,anio,precio,disponible,nombre_marca,latitud,longitud,url,url_img)
    }

    fun limpiarCampos(){
        txt_nombre_m.setText("")
        txt_anio_m.setText("")
        txt_precio_m.setText("")
        txt_nomre_marca.setText("")
        txt_latitud.setText("")
        txt_longitud.setText("")
        txt_url.setText("")
        txt_url_img.setText("")
        ch_disponible.setChecked(false)
    }
    fun llenarCampos(modelo:ModeloHTTP):Int{
        txt_nombre_m.setText(modelo.nombre)
        txt_anio_m.setText(modelo.anio_lanzamiento.toString())
        txt_precio_m.setText(modelo.precio.toString())
        txt_nomre_marca.setText(modelo.marca.toString())

        txt_latitud.setText(modelo.latitud)
        txt_longitud.setText(modelo.longitud)
        txt_url.setText(modelo.url)
        txt_url_img.setText(modelo.url_img)
        ch_disponible.setChecked(modelo.disponible)
        return modelo.id
    }
    fun parseModelos(modelo:Modelo) : List<Pair<String, String>>? {
        val con = ConexionMarcas()
        var parametrosUsuario : List<Pair<String, String>>? = null
        val idMarca = con.encuentraMarca(modelo.marca)
        if(idMarca != null){
            parametrosUsuario = listOf(
                "nombre" to modelo.nombre,
                "anio_lanzamiento" to modelo.anio_lanzamiento.toString(),
                "precio" to modelo.precio.toString(),
                "disponible" to modelo.disponible.toString(),
                "marca" to idMarca.id.toString(),
                "latitud" to modelo.latitud.toString(),
                "longitud" to modelo.longitud.toString(),
                "url" to modelo.url.toString(),
                "url_img" to modelo.urlImg.toString()
            )
            Log.i("dato","modelo ${parametrosUsuario}")
        }else{
            Log.i("dato","no exite el modelo ${modelo.marca}")
        }
        return parametrosUsuario
    }
}