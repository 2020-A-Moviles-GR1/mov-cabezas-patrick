package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_Ciclo_vida.setOnClickListener {boton ->
            irCicloDeVida()
        }
    }
    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java
        )
        startActivity(intentExplicito)
    }
    override fun onStart() {
        super.onStart()
        Log.i("Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("Activity","onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity","onDestroy")
    }
}