package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class GeocodedWaypoint {

    /**

     * @return
     * *     The geocoderStatus
     */
    /**

     * @param geocoderStatus
     * *     The geocoder_status
     */
    @SerializedName("geocoder_status")
    @Expose
    var geocoderStatus: String? = null
    /**

     * @return
     * *     The placeId
     */
    /**

     * @param placeId
     * *     The place_id
     */
    @SerializedName("place_id")
    @Expose
    var placeId: String? = null
    /**

     * @return
     * *     The types
     */
    /**

     * @param types
     * *     The types
     */
    @SerializedName("types")
    @Expose
    var types: List<String> = ArrayList()
    /**

     * @return
     * *     The partialMatch
     */
    /**

     * @param partialMatch
     * *     The partial_match
     */
    @SerializedName("partial_match")
    @Expose
    var partialMatch: Boolean? = null

}
