package nz.co.panpanini.gothedistance.repository

import nz.co.panpanini.gothedistance.models.GoogleMapsRoute
import rx.Observable

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
interface RouteRepository {

    fun getRoute(origin : String, destination : String): Observable<GoogleMapsRoute>

}