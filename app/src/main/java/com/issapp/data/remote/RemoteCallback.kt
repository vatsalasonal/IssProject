package com.issapp.data.remote

import com.issapp.model.IssAstronautsInfo
import com.issapp.model.IssInfo
import io.reactivex.rxjava3.core.Observable

interface RemoteCallback {

    fun fetchIssPositionInfo() : Observable<IssInfo>
    fun fetchIssAstronautsList(): Observable<IssAstronautsInfo>
}