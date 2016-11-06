package nz.co.panpanini.gothedistance.utils

import com.google.android.gms.maps.model.LatLng
import java.util.*

/**
 * Created by matthew @showgizmo.com> on 28/10/16.
 */

class PolylineDecoder {

    /**
     * Precision should be something like 1E5 or 1E6. For OSRM routes found precision was 1E6, not the original default
     * 1E5.

     * @param encoded
     * *
     * @param precision
     * *
     * @return
     */
    @JvmOverloads fun decode(encoded: String, precision: Double = DEFAULT_PRECISION): List<LatLng> {
        val track = ArrayList<LatLng>()
        var index = 0
        var lat = 0
        var lng = 0

        while (index < encoded.length) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / precision, lng.toDouble() / precision)
            track.add(p)
        }
        return track
    }

    companion object {
        private val DEFAULT_PRECISION = 1E5
    }

}
