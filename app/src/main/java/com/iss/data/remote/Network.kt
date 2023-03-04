package com.iss.data.remote

import com.iss.model.IssAstronautsInfo
import com.iss.model.IssInfo

//port com.example.issapp.model.IssInfo
//import com.retrofit.data.model.Posts
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import com.iss.utils.Constants

interface Network {

    @GET(Constants.ISS_LOCATION)
    fun fetchIssPositionInfo(): Observable<IssInfo>

    @GET(Constants.ISS_ASTRONAUTS)
    fun fetchIssAstronautsList(): Observable<IssAstronautsInfo>

}
