package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_modelos.*

class Modelos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelos)

        var posicion = 0
        val listaModelos = ServicioBDDMemoriaModelos.listaModelos

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaModelos
        )
        lv_modelos.adapter=adaptador
        lv_modelos.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id->
            posicion = position
            llenarCampos(listaModelos.get(position))
        }

        btn_agregar_m.setOnClickListener({
            ServicioBDDMemoriaModelos.agragaModelo(getElementos())
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_actualizar_m.setOnClickListener({
            ServicioBDDMemoriaModelos.eliminarModelo(posicion)
            ServicioBDDMemoriaModelos.agragaModelo(getElementos())
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_eliminar_m.setOnClickListener({
            ServicioBDDMemoriaModelos.eliminarModelo(posicion)
            adaptador.notifyDataSetChanged()
            limpiarCampos()
        })
        btn_limpiar_m.setOnClickListener({
//            limpiarCampos()
            getModelo()
        })
    }
    fun getElementos():Modelo{
        var nombre: String = txt_nombre_m.getText().toString()
        var anio : Int = txt_anio_m.getText().toString().toInt()
        var precio : Float= txt_precio_m.getText().toString().toFloat()
        var nombre_marca : String = txt_nomre_marca.getText().toString()
        var disponible : Boolean = ch_disponible.isChecked
        return Modelo(nombre,anio,precio,disponible,nombre_marca)
    }

fun getModelo(){
    val con = ConexionModelos()
    con.crearModelo()
}

    fun limpiarCampos(){
        txt_nombre_m.setText("")
        txt_anio_m.setText("")
        txt_precio_m.setText("")
        txt_nomre_marca.setText("")
        ch_disponible.setChecked(false)
    }
    fun llenarCampos(modelo:Modelo){
        txt_nombre_m.setText(modelo.nombre)
        txt_anio_m.setText(modelo.anio_lanzamiento.toString())
        txt_precio_m.setText(modelo.precio.toString())
        txt_nomre_marca.setText(modelo.marca)
        ch_disponible.setChecked(modelo.disponible)
    }
}