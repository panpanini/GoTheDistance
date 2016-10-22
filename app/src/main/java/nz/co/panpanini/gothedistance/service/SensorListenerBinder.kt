package nz.co.panpanini.gothedistance.service

import android.os.Binder

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */

class SensorListenerBinder(private val sensorListener: SensorListener) : Binder() {

    fun getCurrentSteps(): Long {
        return sensorListener.getCurrentSteps()
    }

}