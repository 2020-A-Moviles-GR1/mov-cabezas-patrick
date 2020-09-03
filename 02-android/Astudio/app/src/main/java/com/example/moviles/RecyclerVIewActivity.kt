package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_v_iew.*

class RecyclerVIewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_v_iew)

        val listaUsuarios=arrayListOf<UsuarioHttp>()

        listaUsuarios.add(
            UsuarioHttp(
                1,
                1212121212,
                1212121212,
                "1717171717",
                "David",
                "algo@mail.com",
                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                2,
                1313131313,
                1313131313,
                "1818181818",
                "Pablo",
                "algo1@mail.com",
                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                3,
                1414141414,
                1414141414,
                "1919191919",
                "Patrick",
                "algo2@mail.com",
                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecyclerView(
            listaUsuarios,
            this,
            rv_usuarios
        )
    }
    fun iniciarRecyclerView(
        lista: List<UsuarioHttp>,
        actividad: RecyclerVIewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }
}