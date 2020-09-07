package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var numeroActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity", "OnCreate")
        btn_ciclo_vida.setOnClickListener({
            irCicloVida()
        })
        btn_List_view.setOnClickListener({
            irListView()
        })
//        btn_intent_respuesta.setOnClickListener({
//            irIntentRespuesta()
//        })
        btn_intent_implicito.setOnClickListener({
            irImplicito()
        })
        btn_resp_propia.setOnClickListener({
            enviarIntentConRespuestaPropia()
        })
        btn_http.setOnClickListener({
            irHttp()
        })
        btn_intent_respuesta
            .setOnClickListener {
                irAIntentConRespuesta()
            }
        btn_recycler
            .setOnClickListener{
                irRecicleView()
            }
        btn_mapa.setOnClickListener({
            irmapaactivity()
        })

    }
    fun irmapaactivity(){
        var intentExplicito =Intent(this, MapsActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun irRecicleView(){
        var intentExplicito =Intent(this, RecyclerVIewActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun irHttp(){
        var intentExplicito =Intent(this, HttpActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun enviarIntentConRespuestaPropia(){
        var intentExplicito =Intent(this, IntentEnviaParametros::class.java)
        this.startActivityForResult(intentExplicito,305)
    }
    fun irCicloVida(){
        var intentExplicito =Intent(this, CicloVidaActivity::class.java)
        this.startActivity(intentExplicito)
    }

    fun irIntentRespuesta(){
        var intentExplicito =Intent(this, IntentEnviaParametros::class.java)
        intentExplicito.putExtra("numero",2)
        this.startActivity(intentExplicito)
    }

    fun irListView(){
        var intentExplicito =Intent(this, BListViewActivity::class.java)
        this.startActivity(intentExplicito)
    }

    fun irImplicito(){
        var intentconrespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        startActivityForResult(intentconrespuesta,304)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       when(resultCode){ RESULT_OK -> {
        Log.i("resultado","OK")
           when(requestCode){
               304 -> {
                   var uri = data?.data
                   if(uri != null){
                       var cursor=contentResolver.query(uri,null,null,null,null,null)
                       cursor?.moveToFirst()
                       var indiceTelefono = cursor?.getColumnIndex(
                           ContactsContract.CommonDataKinds.Phone.NUMBER
                       )
                       var telefono=cursor?.getString(indiceTelefono!!)
                       cursor?.close()
                       Log.i("resultador","telefono: ${telefono}")
                   }
               }
               305 -> {
                   if(data!=null){
                       val nombre = data.getStringExtra("nombre")
                       val edad = data.getIntExtra("edad",0)
                       Log.i("resultado","Nombre: ${nombre} Edad: ${edad}")
                   }
               }
           }
        }

        Activity.RESULT_CANCELED->{
            Log.i("resultado",":(")
        }}
    }

    fun irAIntentConRespuesta() {
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )
        intentExplicito.putExtra("numero", 2)

        val adrian = Usuario("Adrian",31,Date(),1.0)
        val cachetes = Mascota("Cachetes",adrian)
        val arregloMascotas = arrayListOf<Mascota>(cachetes)

        intentExplicito.putExtra("cachetes", cachetes)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)

        startActivity(intentExplicito)
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
}