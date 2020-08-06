package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

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
        btn_resp_aceptar.setOnClickListener({
            var nombre= "patrick"
            var edad = 31
            var intentRespuesta=Intent()
            intentRespuesta.putExtra("nombre",nombre)
            intentRespuesta.putExtra("edad",edad)
            setResult(
                Activity.RESULT_OK,
                intentRespuesta
            )
            finish()
        })
        btn_resp_cancelar.setOnClickListener({

            var intentCancelado=Intent()
            setResult(
                Activity.RESULT_CANCELED,
                intentCancelado
            )
            finish()
        })

        val cachetes = intent.getParcelableExtra<Mascota>("cachetes")
        if(cachetes != null){
            Log.i("parcelable", "${cachetes.nombre} ${cachetes.duenio?.nombre}")
        }

        val arregloMascotas = intent.getParcelableArrayListExtra<Mascota>("arregloMascotas")
        if(arregloMascotas != null){
            arregloMascotas.forEach {
                Log.i("parcelable", "EN ARREGLO")
                Log.i("parcelable", "${it.nombre} ${it.duenio?.nombre}")
            }
        }
    }
}