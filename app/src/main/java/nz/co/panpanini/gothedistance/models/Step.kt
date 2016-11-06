package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import nz.co.panpanini.gothedistance.models.Distance
import nz.co.panpanini.gothedistance.models.Duration
import nz.co.panpanini.gothedistance.models.EndLocation

class Step {

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
     * *     The htmlInstructions
     */
    /**

     * @param htmlInstructions
     * *     The html_instructions
     */
    @SerializedName("html_instructions")
    @Expose
    var htmlInstructions: String? = null
    /**

     * @return
     * *     The polyline
     */
    /**

     * @param polyline
     * *     The polyline
     */
    @SerializedName("polyline")
    @Expose
    var polyline: Polyline? = null
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
     * *     The travelMode
     */
    /**

     * @param travelMode
     * *     The travel_mode
     */
    @SerializedName("travel_mode")
    @Expose
    var travelMode: String? = null
    /**

     * @return
     * *     The maneuver
     */
    /**

     * @param maneuver
     * *     The maneuver
     */
    @SerializedName("maneuver")
    @Expose
    var maneuver: String? = null

}
