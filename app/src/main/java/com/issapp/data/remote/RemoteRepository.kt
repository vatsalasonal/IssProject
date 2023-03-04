package com.issapp.data.remote

import com.issapp.model.IssAstronautsInfo
import com.issapp.di.IssApp
import com.issapp.model.IssInfo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteRepository @Inject constructor() : RemoteCallback {

    @Inject
    lateinit var network: Network

    init {
        IssApp.issComponent.inject(this)
    }

    override fun fetchIssPositionInfo(): Observable<IssInfo> {
        return network.fetchIssPositionInfo()
    }

    override fun fetchIssAstronautsList(): Observable<IssAstronautsInfo> {
        return network.fetchIssAstronautsList()
    }
}