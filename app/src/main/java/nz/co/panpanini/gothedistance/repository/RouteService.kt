package nz.co.panpanini.gothedistance.repository

import nz.co.panpanini.gothedistance.models.GoogleMapsRoute
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
interface RouteService {

 @GET("maps/api/directions/json")
    fun getRoute(@Query("origin") origin : String, @Query("destination") destination : String,
                 @Query("key") apiKey : String) : Observable<GoogleMapsRoute>

}