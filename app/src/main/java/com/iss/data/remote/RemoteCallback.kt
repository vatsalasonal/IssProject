package com.iss.data.remote

import com.iss.model.IssAstronautsInfo
import com.iss.model.IssInfo
import io.reactivex.rxjava3.core.Observable

interface RemoteCallback {

    fun fetchIssPositionInfo() : Observable<IssInfo>
    fun fetchIssAstronautsList(): Observable<IssAstronautsInfo>
}