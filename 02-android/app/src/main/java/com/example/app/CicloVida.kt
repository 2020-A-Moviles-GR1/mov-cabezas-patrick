package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*
import kotlinx.android.synthetic.main.activity_main.*

class CicloVida : AppCompatActivity() {
    var nuemroActual=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        btn_anadir.setOnClickListener{
            sumarUnValor()
        }
    }
    fun sumarUnValor(){
        nuemroActual = nuemroActual +1
        tv_numero.text=nuemroActual.toString()
    }
    override fun onStart() {
        super.onStart()
        Log.i("Activity","onStart CicloVida")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume CicloVida")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("Activity","onRestart CicloVida ")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity","onPause CicloVida")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity","onStop CicloVida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity","onDestroy")
    }
}