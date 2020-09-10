package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.examen1.models.MarcaHTTP
import kotlinx.android.synthetic.main.activity_marcas.*

class Marcas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas)

        var posicion :Int= 0
        var idMarca=0
        val con = ConexionMarcas()
        var listaMarcas = con.obtenerMarcas()

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMarcas
        )
        lv_marcas.adapter=adaptador
        lv_marcas.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id->
            posicion = position
            idMarca= llenarCampos(listaMarcas.get(position))
            Log.i("List-view","Position $position  :  $idMarca")
        }

        btn_agregar.setOnClickListener {
            val marca = con.crearMarca(parseMarca(getValores()))
            if (marca != null)
                listaMarcas.add(marca)
            limpiarCampos()
            adaptador.notifyDataSetChanged()
        }
        btn_actualizar.setOnClickListener {
            val marca = con.actualizaMarca(parseMarca(getValores()),idMarca)
            if (marca != null){
                listaMarcas.removeAt(posicion)
                listaMarcas.add(marca)
            }
            limpiarCampos()
            adaptador.notifyDataSetChanged()

        }
        btn_eliminar.setOnClickListener({
            var marcaR = con.obtenerMarca(idMarca)
            val marca = con.eliminaMarca(idMarca)
            if (marcaR != null){
                con.eliminaModelos(marcaR)
                listaMarcas.removeAt(posicion)
            }
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_limpiar.setOnClickListener({
            limpiarCampos()
        })
    }

    fun getValores():Marca{
        var nombre : String= txt_nombre.getText().toString()
        var pais : String = txt_pais_origen.getText().toString()
        var anio : Int = txt_anio_creacion.getText().toString().toInt()
        var valor : Float =  txt_valor_marca.getText().toString().toFloat()
        var sucursal : Boolean = ch_sucursal_local.isChecked
        return Marca(nombre,pais,anio,sucursal,valor)
    }
    fun limpiarCampos(){
        txt_nombre.setText("")
        txt_pais_origen.setText("")
        txt_anio_creacion.setText("")
        txt_valor_marca.setText("")
        ch_sucursal_local.setChecked(false)
    }
    fun llenarCampos(marca:MarcaHTTP):Int{
        txt_nombre.setText(marca.nombre)
        txt_pais_origen.setText(marca.pais_origen)
        txt_anio_creacion.setText(marca.anio_creacion.toString())
        txt_valor_marca.setText(marca.valor.toInt().toString())
        ch_sucursal_local.setChecked(marca.sucursal_local)
        return marca.id
    }
    fun parseMarca(marca:Marca) : List<Pair<String, String >> {

        val parametrosUsuario: List<Pair<String, String >> = listOf(
            "nombre" to marca.nombre,
            "pais_origen" to marca.pais_origen,
            "anio_creacion" to marca.anio_creacion.toString(),
            "sucursal_local" to marca.sucursal_local.toString(),
            "valor" to marca.valor.toString()
        )
        return parametrosUsuario
    }
}