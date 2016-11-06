package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Polyline {

    /**

     * @return
     * *     The points
     */
    /**

     * @param points
     * *     The points
     */
    @SerializedName("points")
    @Expose
    var points: String? = null

}
