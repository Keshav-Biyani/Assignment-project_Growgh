package com.keshav.internproject.fragement

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.keshav.internproject.R
import com.keshav.internproject.databinding.FragmentMapBinding


class MapFragment : Fragment() , OnMapReadyCallback {
    private var mapView: MapView? = null
//    var currentLocation: Location? = null

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var currentLocation: Location? = null
    private lateinit var locationRequest: LocationRequest
    private var binding : FragmentMapBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMapBinding.inflate(inflater,container,false)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.toolbar?.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        mapView =  binding?.gmGoogleMap!!

        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)


        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        checkLocationPermission()
        currentLocation = getCurrentLocation()

    }
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fetchCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION_PERMISSION
            )
        }
    }
    private fun  getCurrentLocation(): Location? {
        return currentLocation
    }
    private fun fetchCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(false)
                .build()
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    currentLocation = locationResult.lastLocation
                    // Update the map with the new current location
                    mapView?.getMapAsync(this@MapFragment)
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION_PERMISSION
            )
        }
    }

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 1001
    }
    override fun onMapReady(googleMap: GoogleMap) {
        currentLocation?.let { location ->
            val latLng = LatLng(location.latitude, location.longitude)
            val markerOptions = MarkerOptions().position(latLng).title("My Location")
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            googleMap.addMarker(markerOptions)
            googleMap.uiSettings.apply {
                isZoomControlsEnabled = true
                isZoomGesturesEnabled = true // Enable finger zoom gestures
            }
        }
    }


}