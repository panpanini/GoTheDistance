package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class GoogleMapsRoute {


    /**
     * @return The geocodedWaypoints
     */
    /**
     * @param geocodedWaypoints The geocoded_waypoints
     */
    @SerializedName("geocoded_waypoints")
    @Expose
    var geocodedWaypoints: List<GeocodedWaypoint> = ArrayList()
    /**
     * @return The routes
     */
    /**
     * @param routes The routes
     */
    @SerializedName("routes")
    @Expose
    var routes: List<Route> = ArrayList()
    /**
     * @return The status
     */
    /**
     * @param status The status
     */
    @SerializedName("status")
    @Expose
    var status: String? = null

}
