package com.issapp.di

import com.issapp.data.remote.RemoteRepository
import com.issapp.ui.IssViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface IssComponent {

    fun inject(model: IssViewModel)

    //New code for retrofit injection
    fun inject(repository: RemoteRepository)
}
