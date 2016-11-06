package nz.co.panpanini.gothedistance.repository

import android.content.Context
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.models.GoogleMapsRoute
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by matthew <matthew@showgizmo.com> on 22/10/16.
 */
class RetrofitRouteRepository(context : Context) : RouteRepository {

    private val retrofit : Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiKey : String = context.getString(R.string.google_maps_api_key)

    override fun getRoute(origin: String, destination: String): Observable<GoogleMapsRoute> {
        val service : RouteService = retrofit.create(RouteService::class.java)

        return service.getRoute(origin, destination, apiKey)
    }

}