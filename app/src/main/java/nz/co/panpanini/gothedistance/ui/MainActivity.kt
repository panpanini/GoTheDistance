package nz.co.panpanini.gothedistance.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.google.gson.Gson
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.models.GoogleMapsRoute
import nz.co.panpanini.gothedistance.service.SensorListener
import nz.co.panpanini.gothedistance.service.SensorListenerServiceConnection

class MainActivity : AppCompatActivity() {

    val textView : TextView by lazy { findViewById(R.id.current_steps) as TextView }
    val totalDistance : TextView by lazy { findViewById(R.id.total_distance) as TextView }
    val currentDistance : TextView by lazy { findViewById(R.id.current_distance) as TextView }

    val sensorServiceConnection = SensorListenerServiceConnection()

    var isOnScreen = false

    private var googleMapsRoute : GoogleMapsRoute? = null

    companion object {
        private val route = "ROUTE"
        fun createIntent(googleRoute: GoogleMapsRoute, context: Context) : Intent {
            val intent = Intent(context, MainActivity::class.java)
            val gson = Gson()

            intent.putExtra(route, gson.toJson(googleRoute))

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra(route)) {
            val gson = Gson()

            googleMapsRoute = gson.fromJson(intent.getStringExtra(route), GoogleMapsRoute::class.java)
        }

        startService(createServiceIntent())
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
        textView.text = String.format(getString(R.string.current_steps),
                sensorServiceConnection.getCurrentStepCount() )

        val currentWalked = String.format("%.2fkm", ( sensorServiceConnection.getCurrentStepCount() * 0.7 ) / 1000)
        currentDistance.text = String.format(getString(R.string.currently_walked), currentWalked)
        totalDistance.text = googleMapsRoute?.routes?.first()?.legs?.first()?.distance?.text

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
