package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class IntentEnviaParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)

        val numeroEncontrada = intent.getIntExtra("numero",0)
        Log.i("intents","el numero encontrado es: ${numeroEncontrada}")

        val textoCompartido : String?=intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido != null){
            Log.i("intents","el texto es: ${textoCompartido}")
        }
    }
}