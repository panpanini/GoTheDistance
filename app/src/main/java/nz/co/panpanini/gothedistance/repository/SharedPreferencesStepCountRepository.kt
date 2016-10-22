package nz.co.panpanini.gothedistance.repository

import android.content.Context
import android.content.SharedPreferences
import com.github.ajalt.timberkt.d

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
class SharedPreferencesStepCountRepository(context : Context) : StepCountRepository {

    private val preferencesName = "StepsCount"
    private val preferencesKey = "CURRENT_STEP_COUNT"

    val preferences : SharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    override fun getCurrentStepCount(): Long {
        return preferences.getLong(preferencesKey, 0)
    }

    override fun updateStepCount(steps: Long): Long {
        // check if steps is higher than our current count
        if (steps >= getCurrentStepCount()) {
            // incremental update - add the difference
            setSteps(steps)
        } else {
            // phone has probably rebooted - add it as-is
            setSteps(steps + getCurrentStepCount())
        }

        return getCurrentStepCount()
    }

    override fun resetStepCount() {
        setSteps(0)
    }

    fun setSteps(steps : Long) {
        d {
            "setSteps: $steps"
        }
        preferences.edit().putLong(preferencesKey, steps).apply()
    }

}