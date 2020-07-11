package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        btn_anadir.setOnClickListener({
            sumaNumero()
        })
    }

    var numActual=0

    fun sumaNumero(){
        numActual=numActual+1
        tv_numero.text=numActual.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity", "OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity", "OnRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "OnDestroy")
    }
    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        Log.i("activity", "onSaveInstanceState")
        outState?.run {
            putInt("numeroActualGuardado", numActual)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("activity", "onRestoreInstanceState")
        val valorRecuperado = savedInstanceState
            ?.getInt("numeroActualGuardado")

        if(valorRecuperado != null){
            this.numActual = valorRecuperado
            tv_numero.text = this.numActual.toString()
        }
}}