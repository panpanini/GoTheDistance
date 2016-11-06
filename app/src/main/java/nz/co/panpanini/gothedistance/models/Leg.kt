package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Leg {

    /**

     * @return
     * *     The distance
     */
    /**

     * @param distance
     * *     The distance
     */
    @SerializedName("distance")
    @Expose
    var distance: Distance? = null
    /**

     * @return
     * *     The duration
     */
    /**

     * @param duration
     * *     The duration
     */
    @SerializedName("duration")
    @Expose
    var duration: Duration? = null
    /**

     * @return
     * *     The endAddress
     */
    /**

     * @param endAddress
     * *     The end_address
     */
    @SerializedName("end_address")
    @Expose
    var endAddress: String? = null
    /**

     * @return
     * *     The endLocation
     */
    /**

     * @param endLocation
     * *     The end_location
     */
    @SerializedName("end_location")
    @Expose
    var endLocation: EndLocation? = null
    /**

     * @return
     * *     The startAddress
     */
    /**

     * @param startAddress
     * *     The start_address
     */
    @SerializedName("start_address")
    @Expose
    var startAddress: String? = null
    /**

     * @return
     * *     The startLocation
     */
    /**

     * @param startLocation
     * *     The start_location
     */
    @SerializedName("start_location")
    @Expose
    var startLocation: StartLocation? = null
    /**

     * @return
     * *     The steps
     */
    /**

     * @param steps
     * *     The steps
     */
    @SerializedName("steps")
    @Expose
    var steps: List<Step> = ArrayList()
    /**

     * @return
     * *     The trafficSpeedEntry
     */
    /**

     * @param trafficSpeedEntry
     * *     The traffic_speed_entry
     */
    @SerializedName("traffic_speed_entry")
    @Expose
    var trafficSpeedEntry: List<Any> = ArrayList()
    /**

     * @return
     * *     The viaWaypoint
     */
    /**

     * @param viaWaypoint
     * *     The via_waypoint
     */
    @SerializedName("via_waypoint")
    @Expose
    var viaWaypoint: List<Any> = ArrayList()

}
