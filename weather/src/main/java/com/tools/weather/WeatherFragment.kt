package com.tools.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.*
import com.tools.core.BaseFragment
import com.tools.core.network.Status
import com.tools.jackofall.di.FeatureModuleDependency
import com.tools.news.injection.DaggerWeatherComponent
import com.tools.weather.databinding.FragmentWeatherBinding
import dagger.hilt.android.EntryPointAccessors
import timber.log.Timber
import javax.inject.Inject


class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {

    val backgroundColor = Color(0xFF7298A1)


    val temprature = mutableStateOf("_ _")

    val currentWeatherIcon = mutableStateOf(R.drawable.weather_01d)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val weatherViewModel: WeatherViewModel by viewModels { viewModelFactory }

    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    companion object {
        @JvmStatic
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    override fun getFragmentLayout() = R.layout.fragment_weather

    override fun getToolBar() = binding.appbar.toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerWeatherComponent.builder()
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FeatureModuleDependency::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.composeView.setContent {
            Content()
        }

        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
        ) {
            mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(requireActivity())

            getLastLocation()
        } else {
            Toast.makeText(
                context,
                "This device is not supported", Toast.LENGTH_LONG
            )
                .show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnSuccessListener {

                    if (it == null) {
                        requestNewLocationData()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "${it.latitude} And ${it.longitude}",
                            Toast.LENGTH_LONG
                        ).show()
                        getCurrentWeather(it.latitude.toString(), it.longitude.toString())
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            Toast.makeText(
                requireContext(),
                "${mLastLocation.latitude} And ${mLastLocation.longitude}",
                Toast.LENGTH_LONG
            ).show()
            getCurrentWeather(mLastLocation.latitude.toString(), mLastLocation.longitude.toString())
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }


    @Composable
    fun Content() {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = currentWeatherIcon.value),
                    contentDescription = "Weather Image"
                )

            }


            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = temprature.value,
                    Modifier
                        .align(
                            Alignment.Center
                        )
                        .padding(end = 20.dp, top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 100.sp
                )
                Text(
                    text = "o",
                    modifier = Modifier.align(Alignment.TopEnd),
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )

            }


        }

    }


    @Preview
    @Composable
    fun ContentPreview() {
        Content()
    }

    private fun getCurrentWeather(lat: String, lon: String) {
        weatherViewModel.getCurrentWeather(lat, lon)
            .observe(viewLifecycleOwner, Observer { response ->
                response?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let {
                                temprature.value = it.data.current.weather.tp.toString()
                                currentWeatherIcon.value =
                                    WeatherUtils.getIcon(it.data.current.weather.ic)
                            }
                        }
                        Status.ERROR -> {
                            Timber.d("Error while getting currency data")
                            showErrorAlertMessage()
                        }
                    }
                }
            })

    }


}