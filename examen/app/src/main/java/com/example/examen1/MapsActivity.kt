package com.example.examen1

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

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

        listaModelos.forEach {
            anadirMarcador(it.latitud,it.longitud,it.nombre)
        }

        val puntoU = LatLng(-0.176714, -78.485399)
        val zoom = 15f
        moverCamaraConZoom(puntoU,zoom)

        mMap.setOnMarkerClickListener(this)
    }

    fun anadirMarcador(latitude:String,longitud:String,title:String){
        Log.i("ubicaciones","${latitude.toDouble()} - ${longitud.toDouble()}")
        mMap.addMarker(
            MarkerOptions().position(LatLng(latitude.toDouble(),longitud.toDouble())).title(title)
        
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
        Log.i("mapa","Polyline onMarkerClick ${p0.toString()}")
        if (p0 != null)
            p0.showInfoWindow()
        return false
    }
}
