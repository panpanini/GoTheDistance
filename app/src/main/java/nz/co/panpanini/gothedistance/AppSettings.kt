package nz.co.panpanini.gothedistance

import android.content.Context
import com.google.gson.Gson
import nz.co.panpanini.gothedistance.models.GoogleMapsRoute

/**
 * Created by matthew <matthew@showgizmo.com> on 27/10/16.
 */
class AppSettings(context: Context) {

    private val preferencesKey = "AppSettingsPreferences"
    private val routeKey = "CurrentRoute"

    val prefs = context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE)

    fun getCurrentRoute() : GoogleMapsRoute? {
        val routeJson : String? = prefs.getString(routeKey, null)
        var route : GoogleMapsRoute? = null

        routeJson?.let {
            val gson = Gson()
            route = gson.fromJson(routeJson, GoogleMapsRoute::class.java)
        }

        return route
    }

    fun storeCurrentRoute(route: GoogleMapsRoute) {
        val gson = Gson()

        val routeJson = gson.toJson(route)

        prefs.edit()
            .putString(routeKey, routeJson)
            .apply()
    }

    fun removeCurrentRoute() {
        prefs.edit().remove(routeKey).apply()
    }
}