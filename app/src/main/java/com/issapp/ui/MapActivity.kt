package com.issapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.issapp.R
import com.issapp.model.IssInfo
import com.issapp.model.IssPeopleDate
import com.issapp.utils.LocationDateConverter
import io.reactivex.rxjava3.core.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.random.Random

class MapActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    private lateinit var textViewDistance: TextView
    private lateinit var adapter: AstronautIssLocationTimeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewIssLocationDateDetail: RecyclerView
    val colors =
        intArrayOf(Color.BLACK, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.DKGRAY)

    var issInfoList = ArrayList<IssInfo>()

    var issLocationTimesList = kotlin.collections.ArrayList<IssPeopleDate>()

    private lateinit var geocoder: Geocoder

    private lateinit var issInfo: IssInfo
    private var googleMap: GoogleMap? = null

    private val model by lazy { ViewModelProvider(this).get(IssViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        textViewDistance = findViewById(R.id.textViewDistance)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewIssLocationDateDetail = findViewById(R.id.recyclerViewIssLocationDateDetail)
        geocoder = Geocoder(this, Locale.getDefault())
        fusedLocationProviderClient =  LocationServices.getFusedLocationProviderClient(this@MapActivity)
        fetchLocation()
        textViewDistance.text = getString(R.string.gps_location)
        model.issInfo.observe(this, Observer {
            issInfo = it
            issInfoList.add(it)
            val randomValue = Random.nextInt(colors.size)
            val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
            val startPoint = Location("locationA")
            startPoint.latitude = latLng.latitude
            startPoint.longitude = latLng.longitude

            val endPoint = Location("locationB")
            endPoint.latitude = it.iss_position?.latitude!!
            endPoint.longitude = it.iss_position?.longitude!!

            var distance = startPoint.distanceTo(endPoint).toDouble()
            distance *= 0.001
            textViewDistance.text = getString(R.string.distance).plus(String.format("%.2f", distance))
                .plus(" km")
            textViewDistance.setTextColor(colors.get(randomValue))
            var issLocationTimes = IssPeopleDate(AstronautIssLocationTimeAdapter.VIEW_TYPE_TWO,
                LocationDateConverter.getAddress(it.iss_position?.latitude!!,  it.iss_position?.longitude!!, geocoder).toString(),
            LocationDateConverter.getDate(it.timestamp!!).toString())

            issLocationTimesList.add(issLocationTimes)
            adapter = AstronautIssLocationTimeAdapter(this, issLocationTimesList)
            val gridLayoutManager = GridLayoutManager(this, 3)
            recyclerViewIssLocationDateDetail.layoutManager = gridLayoutManager
            recyclerViewIssLocationDateDetail.adapter = adapter
            adapter.notifyDataSetChanged()
            if(it != null && googleMap != null) {
                googleMap?.clear()
                var issLatLng = LatLng(it.iss_position?.latitude!!, it.iss_position?.longitude!!)
                onMarkerDisplay(issLatLng, 0f)
            }
        })

        model.astronautsList.observe(this, Observer {
            var list = it?.people
            var peopleList = ArrayList<IssPeopleDate>()
            for(people in list!!) {
                var crafts = people.craft
                if (crafts.equals(getString(R.string.crafts_ISS), true))
                    peopleList.add(IssPeopleDate(AstronautIssLocationTimeAdapter.VIEW_TYPE_ONE, people.name, people.craft))
            }
            adapter = AstronautIssLocationTimeAdapter(this, peopleList)
            val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })

    }

    private fun onIntervalApiCall() {
        Observable.interval(5, TimeUnit.SECONDS)
            .doOnNext { n -> model.fetchIssPositionInfo() }
            .repeat()
            .subscribe()
    }

    override fun onResume() {
        super.onResume()
        onIntervalApiCall()
        model.fetchIssAstronautsList()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            val location = it
            if (location != null) {
                currentLocation = location
                Toast.makeText(applicationContext, currentLocation.latitude.toString() + "" +
                        currentLocation.longitude, Toast.LENGTH_SHORT).show()
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.myMap) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this@MapActivity)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        textViewDistance.text = getString(R.string.gps_location)
            .plus(":" ).plus(LocationDateConverter.getAddress(latLng.latitude, latLng.longitude, geocoder))
        onMarkerDisplay(latLng, 10.0f)
    }

    private fun onMarkerDisplay(latLng: LatLng, zoomValue: Float) {
        val markerOptions = MarkerOptions().position(latLng).title("I am here!")
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
        googleMap?.addMarker(markerOptions)
        googleMap?.setMaxZoomPreference(zoomValue)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomValue))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
            PackageManager.PERMISSION_GRANTED) {
            fetchLocation()
        }
        }
    }
}