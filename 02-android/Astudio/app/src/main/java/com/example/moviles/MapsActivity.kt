package com.example.moviles

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
        GoogleMap.OnPolygonClickListener
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
        establecerConfiguracionMapa(mMap)
        establecerListeners(mMap)
        val cci = LatLng(-0.176714, -78.485399)
        val titulo = "CCI"
        val zoom = 17f
        val puntoU= LatLng(-0.177925, -78.477365)
        anadirMarcador(cci,titulo)
        moverCamaraConZoom(puntoU,zoom)
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        val polilineaUno = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(-0.177925, -78.477365),
                    LatLng(-0.177925, -78.477365),
                    LatLng(-0.177925, -78.477365),
                    LatLng(-0.177925, -78.477365)
                )
        )
        val poligonoUno = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    LatLng(-0.207374, -78.487135),
                    LatLng(-0.208519, -78.488956),
                    LatLng(-0.208042, -78.490131),
                    LatLng(-0.206706, -78.488055)
                )
        )
        poligonoUno.fillColor = -0xc77c4


//        establecerConfiguracionMapa(nMap)



    }
    fun anadirMarcador(lating:LatLng,title:String){
        mMap.addMarker(
            MarkerOptions().position(lating).title(title)
        )
    }

    fun moverCamaraConZoom(lating: LatLng,zoom:Float=10f){
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(lating,zoom)
        )
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }else{
                //para definir la ubicacaion al momento de iniciar la aplicion
                uiSettings.isZoomControlsEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
        }
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
                ),
                1
            )

        }
    }

    fun establecerListeners(map:GoogleMap){
        with(map){
            setOnCameraIdleListener( this@MapsActivity )
//            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
        }
    }
    override fun onCameraMoveCanceled() {
        Log.i("mapa","Empezando a mover onCameraMoveStarted")
    }

    override fun onCameraMove() {
        Log.i("mapa","moviendo onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("mapa","quieto onCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa","Polyline onPolylineClick ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","Polygon onPolygonClick ${p0.toString()}")
    }
}