package nz.co.panpanini.gothedistance

import android.app.Application
import timber.log.Timber

/**
 * Created by matthew @showgizmo.com> on 22/10/16.
 */

class GoTheDistance : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
