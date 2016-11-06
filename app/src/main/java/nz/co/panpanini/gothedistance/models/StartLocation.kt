package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StartLocation {

    /**

     * @return
     * *     The lat
     */
    /**

     * @param lat
     * *     The lat
     */
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    /**

     * @return
     * *     The lng
     */
    /**

     * @param lng
     * *     The lng
     */
    @SerializedName("lng")
    @Expose
    var lng: Double? = null

}
