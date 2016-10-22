package nz.co.panpanini.gothedistance.repository

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
interface StepCountRepository {

    /**
     * Returns the current number of steps
     */
    fun getCurrentStepCount() : Long


    /**
     * Updates and returns {steps} steps with the current count
     */
    fun updateStepCount(steps : Long) : Long

    /**
     * Resets the current step count back to 0
     */
    fun resetStepCount()


}