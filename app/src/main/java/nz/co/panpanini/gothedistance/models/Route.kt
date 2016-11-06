package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Route {

    /**

     * @return
     * *     The bounds
     */
    /**

     * @param bounds
     * *     The bounds
     */
    @SerializedName("bounds")
    @Expose
    var bounds: Bounds? = null
    /**

     * @return
     * *     The copyrights
     */
    /**

     * @param copyrights
     * *     The copyrights
     */
    @SerializedName("copyrights")
    @Expose
    var copyrights: String? = null
    /**

     * @return
     * *     The legs
     */
    /**

     * @param legs
     * *     The legs
     */
    @SerializedName("legs")
    @Expose
    var legs: List<Leg> = ArrayList()
    /**

     * @return
     * *     The overviewPolyline
     */
    /**

     * @param overviewPolyline
     * *     The overview_polyline
     */
    @SerializedName("overview_polyline")
    @Expose
    var overviewPolyline: Polyline? = null
    /**

     * @return
     * *     The summary
     */
    /**

     * @param summary
     * *     The summary
     */
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    /**

     * @return
     * *     The warnings
     */
    /**

     * @param warnings
     * *     The warnings
     */
    @SerializedName("warnings")
    @Expose
    var warnings: List<Any> = ArrayList()
    /**

     * @return
     * *     The waypointOrder
     */
    /**

     * @param waypointOrder
     * *     The waypoint_order
     */
    @SerializedName("waypoint_order")
    @Expose
    var waypointOrder: List<Any> = ArrayList()

}
