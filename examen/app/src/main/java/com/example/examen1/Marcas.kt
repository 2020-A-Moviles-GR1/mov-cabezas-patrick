package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_marcas.*

class Marcas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas)

        var posicion = 0
//        val listaMarcas = ServicioBDDMemoriaMarcas.listaMarcas
        val listaMarcas = ArrayList<Marca>()

        val con = ConexionMarcas()
        con.obtenerModelo(listaMarcas)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMarcas
        )
        lv_marcas.adapter=adaptador
        lv_marcas.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id->
            posicion = position
            llenarCampos(listaMarcas.get(position))
            Log.i("List-view","Position $position")
        }

        btn_agregar.setOnClickListener({
            con.crearMarca(getValores())
            listaMarcas.add(getValores())
//            ServicioBDDMemoriaMarcas.agragaMarca(getValores())
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_actualizar.setOnClickListener({
            con.actualizaMarca(getValores())
            ServicioBDDMemoriaMarcas.eliminarMarca(posicion)
            ServicioBDDMemoriaMarcas.agragaMarca(getValores())
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_eliminar.setOnClickListener({
            con.eliminaMarca(getValores())
            ServicioBDDMemoriaMarcas.eliminarMarca(posicion)
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
    fun llenarCampos(marca:Marca){
        txt_nombre.setText(marca.nombre)
        txt_pais_origen.setText(marca.pais_origen)
        txt_anio_creacion.setText(marca.anio_creacion.toString())
        txt_valor_marca.setText(marca.valor.toInt().toString())
        ch_sucursal_local.setChecked(marca.sucursal_local)
    }
}