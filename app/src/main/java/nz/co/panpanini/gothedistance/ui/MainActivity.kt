package nz.co.panpanini.gothedistance.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.service.SensorListenerServiceConnection
import nz.co.panpanini.gothedistance.service.SensorListener

class MainActivity : AppCompatActivity() {

    val textView : TextView by lazy {
        findViewById(R.id.current_steps) as TextView
    }

    val sensorServiceConnection = SensorListenerServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(createServiceIntent())
    }

    override fun onResume() {
        super.onResume()
        bindService(createServiceIntent(), sensorServiceConnection, 0)

        updateUI()
    }

    override fun onPause() {
        super.onPause()
        unbindService(sensorServiceConnection)
    }

    private fun updateUI() {
        textView.text = String.format(getString(R.string.current_steps),
                sensorServiceConnection.getCurrentStepCount() )

        Handler().postDelayed({
            updateUI()
        }, 1000) // update every second
    }

    private fun createServiceIntent() : Intent {
        return Intent(this, SensorListener::class.java) // TODO: this is kinda ugly. use Anko instead?

    }

}
