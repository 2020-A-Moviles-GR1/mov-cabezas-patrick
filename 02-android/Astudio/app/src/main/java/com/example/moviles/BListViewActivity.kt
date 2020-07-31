package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_view.*
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class BListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)
        val listEntrenadores = arrayListOf<Entrenador>()
        listEntrenadores.add(Entrenador("Adrian","Eguaz"))
        listEntrenadores.add(Entrenador("Vicente","Sarzosa"))
        listEntrenadores.add(Entrenador("Wendy","Moises"))
        listEntrenadores.add(Entrenador("Ivan","Parra"))
        listEntrenadores.add(Entrenador("Juan","Duran"))
        listEntrenadores.add(Entrenador("Andrea","Lara"))
        listEntrenadores.add(Entrenador("Lisa","Guerrero"))
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listEntrenadores
        )
        lv_numeros.adapter=adaptador
        lv_numeros.onItemClickListener=AdapterView.OnItemClickListener{
            parent,view,position,id->
                Log.i("List-view","Position $position")
        }
        btn_actualiza.setOnClickListener({
            mostrar_actulizados(listEntrenadores,adaptador)
        })
    }

    fun mostrar_actulizados(
        listEntrenadores: ArrayList<Entrenador>,
        adaptador: ArrayAdapter<Entrenador>
    ){
        listEntrenadores.add(Entrenador("Lisa","Guerrero"))
        adaptador.notifyDataSetChanged()
    }
//    override fun onSaveInstanceState(
//        outState: Bundle
//    ) {
//        Log.i("activity", "onSaveInstanceState")
//        outState?.run {
//            putInt("numeroActualGuardado", lv_numeros[0])
//        }
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(
//        savedInstanceState: Bundle
//    ) {
//        super.onRestoreInstanceState(savedInstanceState)
//        Log.i("activity", "onRestoreInstanceState")
//        val valorRecuperado = savedInstanceState
//            ?.getInt("numeroActualGuardado")
//
//        if(valorRecuperado != null){
//            this.lv_numeros = valorRecuperado
//            tv_numero.text = this.numActual.toString()
//        }
//    }


}