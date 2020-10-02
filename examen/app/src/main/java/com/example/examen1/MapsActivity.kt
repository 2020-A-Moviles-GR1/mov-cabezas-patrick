package com.example.examen1

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.examen1.models.ModeloHTTP
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.net.URL

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnCameraMoveCanceledListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener,
    GoogleMap.OnMarkerClickListener
{

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    var lista_marcadores: List<Int> = listOf(
        R.drawable.marcador0,
        R.drawable.marcador1,
        R.drawable.marcador2,
        R.drawable.marcador3,
        R.drawable.marcador4,
        R.drawable.marcador5,
        R.drawable.marcador6,
        R.drawable.marcador7,
        R.drawable.marcador8,
        R.drawable.marcador9,
        R.drawable.marcador10,
        R.drawable.marcador11,
        R.drawable.marcador12
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        solictitarPermisos()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var con = ConexionModelos()
        val listaModelos = con.obtenerModelos()
        var contador=0
//        val markerIcon: BitmapDescriptor = getMarkerIconFromDrawable(circleDrawable)
        listaModelos.forEach {
            anadirMarcador(it.latitud,it.longitud,it.nombre,it.url,contador)
            contador = contador+1
        }

        val puntoU = LatLng(-0.176714, -78.485399)
        val zoom = 15f
        moverCamaraConZoom(puntoU,zoom)

        mMap.setOnMarkerClickListener(this)

    }

    fun getUrlFromIntent(url: String) {
//        var Url = "http://www.google.com"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://"+url)
        startActivity(intent)
    }

    fun anadirMarcador(latitude:String,longitud:String,title:String,url:String,contador:Int){
        Log.i("ubicaciones","${latitude.toDouble()} - ${longitud.toDouble()}")


        var imagen = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)

        try {
            imagen = BitmapDescriptorFactory.fromResource(lista_marcadores.get(contador))
        }catch (e: Exception) {
            Log.i("Error ####","no se udo cargasr ${e}")
        }

        mMap.addMarker(
            MarkerOptions().position(LatLng(latitude.toDouble(),longitud.toDouble())).title(title)
                .snippet(url)
                .anchor(0.05f,0.05f)
                .icon(imagen)
        )
    }

    fun solictitarPermisos(){
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if(tienePermisos){
            Log.i("mapa","Tiene permisos FINE Locaction")
            this.tienePermisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),1
            )
        }
    }

    fun moverCamaraConZoom(lating: LatLng,zoom:Float=10f){
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(lating,zoom)
        )
    }

    override fun onCameraMoveCanceled() {
        TODO("Not yet implemented")
    }

    override fun onCameraMove() {
        TODO("Not yet implemented")
    }

    override fun onCameraIdle() {
        TODO("Not yet implemented")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa","Polyline onPolylineClick ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","Polyline onPolygonClick ${p0.toString()}")
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        if (p0 != null) {
            Log.i("mapa", "Polyline onMarkerClick ${p0.snippet}")
            getUrlFromIntent(p0.snippet)
        }
        return false
    }

//    private fun getMarkerIcon(context: Context, cancion: ModeloHTTP, listener: (BitmapDescriptor) -> Unit) {
//        val markerView = View.inflate(context, R.layout.map_maker, null)
//        Glide.with(context)
//            .asBitmap()
//            .load(cancion.url_img)
//            .into(object : SimpleTarget<Bitmap>() {
//
//
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
//                ) {
//                    markerView.map_container.setImageBitmap(resource)
//                    listener.invoke(BitmapDescriptorFactory.fromBitmap(getBitmapFromView(markerView)))
//                }
//            })}


//    private fun getBitmapFromView(view: View): Bitmap {
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
//        view.draw(canvas)
//        return bitmap
//    }
}
