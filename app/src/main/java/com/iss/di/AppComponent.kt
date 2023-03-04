package com.iss.di

import com.iss.data.remote.RemoteRepository
import com.iss.ui.IssViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(model: IssViewModel)

    //New code for retrofit injection
    fun inject(repository: RemoteRepository)
}