package com.example.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_marcas.setOnClickListener({
            irMarcas()
        })
        btn_modelos.setOnClickListener({
            irModelos()
        })
        btn_maps.setOnClickListener({
            irMapas()
        })
    }
    fun irMarcas(){
        var intentExplicito = Intent(this, Marcas::class.java)
        this.startActivity(intentExplicito)
    }
    fun irModelos(){
        var intentExplicito = Intent(this, Modelos::class.java)
        this.startActivity(intentExplicito)
    }
    fun irMapas(){
        var intentExplicito = Intent(this, MapsActivity::class.java)
        this.startActivity(intentExplicito)
    }
}