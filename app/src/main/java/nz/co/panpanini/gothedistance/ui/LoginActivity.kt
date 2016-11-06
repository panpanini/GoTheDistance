package nz.co.panpanini.gothedistance.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import nz.co.panpanini.gothedistance.AppSettings
import nz.co.panpanini.gothedistance.R
import nz.co.panpanini.gothedistance.models.GoogleMapsRoute
import nz.co.panpanini.gothedistance.repository.RetrofitRouteRepository
import nz.co.panpanini.gothedistance.repository.SharedPreferencesStepCountRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    val routeRepo by lazy { RetrofitRouteRepository(this) }

    val heightInput by lazy { findViewById(R.id.height_edit_text) as EditText }

    val fromInput by lazy { findViewById(R.id.from_edit_text) as EditText }
    val toInput by lazy { findViewById(R.id.to_edit_text) as EditText }

    val submitButton by lazy { findViewById(R.id.start_run_button) as Button }

    val appSettings by lazy { AppSettings(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // check if we have a route already - if we do, just open the main view
        val route = appSettings.getCurrentRoute()

        route?.let {
            openRoute(route)
        }


        submitButton.setOnClickListener {
                routeRepo.getRoute(fromInput.text.toString(), toInput.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    appSettings.storeCurrentRoute(it)
                    val repo = SharedPreferencesStepCountRepository(LoginActivity@this)
                    repo.resetStepCount() // we're starting a new walk
                    openRoute(it)
                }
        }
    }


    private fun openRoute(route : GoogleMapsRoute) {
        val intent = Intent(this, MapsActivity::class.java)//MainActivity.createIntent(route, LoginActivity@this)
        startActivity(intent)
        finish()
    }
}
