package nz.co.panpanini.gothedistance.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import nz.co.panpanini.gothedistance.AppSettings
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.service.SensorListener
import nz.co.panpanini.gothedistance.service.SensorListenerServiceConnection
import nz.co.panpanini.gothedistance.utils.PolylineDecoder

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    private val decoder = PolylineDecoder()

    private val googleMapsRoute by lazy {
        AppSettings(MapsActivity@this).getCurrentRoute()
    }

    val totalDistance : TextView by lazy { findViewById(R.id.total_distance) as TextView }
    val currentDistance : TextView by lazy { findViewById(R.id.current_distance) as TextView }

    val sensorServiceConnection = SensorListenerServiceConnection()

    var isOnScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        startService(createServiceIntent())
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

        Handler().postDelayed(
                {
                    val polylineOptions = PolylineOptions()
                    polylineOptions.addAll(decoder.decode(googleMapsRoute!!.routes[0].overviewPolyline?.points!!))
                    polylineOptions.color(Color.RED)

                    val builder = LatLngBounds.Builder()
                    polylineOptions.points.forEach { builder.include(it) }

                    mMap?.addPolyline(polylineOptions)
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 50))
                }, 500
        )

    }

    override fun onResume() {
        super.onResume()
        bindService(createServiceIntent(), sensorServiceConnection, 0)
        isOnScreen = true
        updateUI()
    }

    override fun onPause() {
        super.onPause()
        unbindService(sensorServiceConnection)
        isOnScreen = false
    }

    private fun updateUI() {
        val currentWalked = String.format("%.2fkm", sensorServiceConnection.getCurrentDistanceKilometres())
        currentDistance.text = String.format(getString(R.string.currently_walked), currentWalked)
        totalDistance.text = String.format(getString(R.string.total_distance),
                googleMapsRoute?.routes?.first()?.legs?.first()?.distance?.text)

        if (isOnScreen) {
            Handler().postDelayed({
                updateUI()
            }, 1000) // update every second
        }
    }

    private fun createServiceIntent() : Intent {
        return Intent(this, SensorListener::class.java) // TODO: this is kinda ugly. use Anko instead?

    }
}
