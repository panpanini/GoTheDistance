package nz.co.panpanini.gothedistance.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Bounds {

    /**

     * @return
     * *     The northeast
     */
    /**

     * @param northeast
     * *     The northeast
     */
    @SerializedName("northeast")
    @Expose
    var northeast: Northeast? = null
    /**

     * @return
     * *     The southwest
     */
    /**

     * @param southwest
     * *     The southwest
     */
    @SerializedName("southwest")
    @Expose
    var southwest: Southwest? = null

}
