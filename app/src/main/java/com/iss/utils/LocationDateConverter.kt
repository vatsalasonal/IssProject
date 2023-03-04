package com.iss.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.text.format.DateFormat
import android.util.Log
import java.io.IOException
import java.util.*
import com.iss.R

object LocationDateConverter {

    fun getDate(timestamp: Long) :String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        val date = DateFormat.format("MMM dd, yyyy HH:mm:ss",calendar).toString()
        return date
    }

    fun getAddress(latitude: Double, longitude: Double, geocoder: Geocoder): String? {
        val result = StringBuilder()
        try {
            val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 3)
            if (addresses.isNotEmpty()) {
                val address: Address = addresses[0]
                result.append(address.countryName)
            }
        } catch (e: IOException) {
            Log.e("tag", e.message
            )
        }
        return result.toString()
    }
    //TODO: check if we can use this later
    fun getCityName(lat: Double,long: Double, geocoder: Geocoder):String{
        var cityName: String?
        val address = geocoder.getFromLocation(lat,long,3)
        cityName = address[0].adminArea
        if (cityName == null){
            cityName = address[0].locality
            if (cityName == null){
                cityName = address[0].subAdminArea
            }
        }
        return cityName
    }

    fun getLocationName(lat: Double,long: Double, context: Context, geocoder: Geocoder): String {
        var locationName = getAddress(lat,  long, geocoder).toString()
         locationName = if (locationName.isNullOrEmpty())
            context.getString(R.string.latitude).plus(String.format("%.2f", lat))
                .plus(" ").plus(context.getString(R.string.longitude)).plus(String.format("%.2f", long))
        else
            locationName.plus(" ").plus(LocationDateConverter.getCityName(lat,  long, geocoder).toString())
        return locationName
    }

}