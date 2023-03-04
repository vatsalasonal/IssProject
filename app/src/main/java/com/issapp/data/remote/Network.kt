package com.issapp.data.remote

import com.issapp.model.IssAstronautsInfo
import com.issapp.model.IssInfo

//port com.example.issapp.model.IssInfo
//import com.retrofit.data.model.Posts
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import com.issapp.utils.Constants

interface Network {

    @GET(Constants.ISS_LOCATION)
    fun fetchIssPositionInfo(): Observable<IssInfo>

    @GET(Constants.ISS_ASTRONAUTS)
    fun fetchIssAstronautsList(): Observable<IssAstronautsInfo>

}
