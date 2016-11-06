package nz.co.panpanini.gothedistance.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.github.ajalt.timberkt.d
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.repository.SharedPreferencesStepCountRepository
import nz.co.panpanini.gothedistance.repository.StepCountRepository

/**
 * Created by matthew <matthew@showgizmo.com> on 16/10/16.
 */
class SensorListener : Service(), SensorEventListener {

    private val notificationId = 10

    val binder : IBinder = SensorListenerBinder(SensorListener@this)

    val sensorManager: SensorManager by lazy {
        getSystemService(SENSOR_SERVICE) as SensorManager
    }


    val stepCountRepository : StepCountRepository by lazy {
        SharedPreferencesStepCountRepository(this)
    }

    var isBound = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val success = sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_FASTEST, 500)

        d{ "started sensorManager $success"}

        return START_NOT_STICKY
    }


    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val currentSteps = event.values[0]
            d{ "sensor changed: current steps $currentSteps" }

            stepCountRepository.updateStepCount(currentSteps.toLong())

//            updateNotification(currentSteps)

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        d{ "accuracy changed, new accuracy: $accuracy" }
    }


    override fun onBind(intent: Intent?): IBinder? {
        isBound = true
        cancelNotification()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isBound = false
        showNotification()
        return super.onUnbind(intent)
    }

    fun updateNotification(currentSteps : Float) {
        val notificationManager : NotificationManagerCompat = NotificationManagerCompat.from(SensorListener@this)

        val notification : Notification = NotificationCompat.Builder(SensorListener@this)
                .setContentTitle("Current Steps")
                .setContentText("${currentSteps.toInt()}")
                .setSmallIcon(R.drawable.ic_notification_walk)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setLocalOnly(true)
                .build()

        //TODO: only notify if notification is not cancelled, and we haven't re-opened the app
        notificationManager.notify(notificationId, notification)

    }

    private fun cancelNotification() {
        val notificationManager : NotificationManagerCompat = NotificationManagerCompat.from(SensorListener@this)

        notificationManager.cancel(notificationId)
    }

    private fun showNotification() {
        updateNotification(getCurrentSteps().toFloat())
    }

    fun getCurrentSteps() : Long {
        return stepCountRepository.getCurrentStepCount()
    }
}