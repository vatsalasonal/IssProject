package com.iss.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
//import com.example.issapp.model.IssAstronautsInfo
import com.iss.data.remote.RemoteRepository
import com.iss.di.App
import com.iss.model.IssAstronautsInfo
import com.iss.model.IssInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

class IssViewModel @Inject constructor(@NotNull appContext: Application) :
    AndroidViewModel(appContext) {

    @Inject
    lateinit var remoteRepository: RemoteRepository

    private lateinit var disposable: Disposable

    var issInfo = MutableLiveData<IssInfo>()
    var astronautsList = MutableLiveData<IssAstronautsInfo>()
    var toastMsg = MutableLiveData<String>()
    var showLoader = MutableLiveData<Boolean>()

    init {
        App.appComponent.inject(this)
    }

    fun fetchIssPositionInfo() {

        showLoader.postValue(true)
        disposable = remoteRepository.fetchIssPositionInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                issInfo.postValue(it)
                showLoader.postValue(false)
            }, {
                showLoader.postValue(false)
                toastMsg.postValue(it.message)
            })
    }

    fun fetchIssAstronautsList() {

        showLoader.postValue(true)
        disposable = remoteRepository.fetchIssAstronautsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                astronautsList.postValue(it)
                showLoader.postValue(false)
            }, {
                showLoader.postValue(false)
                toastMsg.postValue(it.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}