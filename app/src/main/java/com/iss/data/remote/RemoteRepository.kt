package com.iss.data.remote

import com.iss.model.IssAstronautsInfo
import com.iss.di.App
import com.iss.model.IssInfo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteRepository @Inject constructor() : RemoteCallback {

    @Inject
    lateinit var network: Network

    init {
        App.appComponent.inject(this)
    }

    override fun fetchIssPositionInfo(): Observable<IssInfo> {
        return network.fetchIssPositionInfo()
    }

    override fun fetchIssAstronautsList(): Observable<IssAstronautsInfo> {
        return network.fetchIssAstronautsList()
    }
}