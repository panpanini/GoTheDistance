package nz.co.panpanini.gothedistance.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
class SensorListenerServiceConnection : ServiceConnection {

    var binder : SensorListenerBinder? = null

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        binder = service as SensorListenerBinder
    }

    fun getCurrentStepCount() : Long {
        binder?.let {
            return binder!!.getCurrentSteps()
        }
        return 0
    }

    fun getCurrentDistanceKilometres() : Float {
        binder?.let {
            val currentSteps = binder!!.getCurrentSteps()

            return ((currentSteps * 0.7f) / 1000)
        }

        return 0f
    }

}