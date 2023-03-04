package com.iss

import dagger.Component
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Component(modules = [TestApplicationComponent.TestAppModule::class])
internal interface TestApplicationComponent {
    fun inject(app: com.iss.TestApplication?)
   // fun inject(remoteRepository: RemoteRepository?)

    @InstallIn(SingletonComponent::class)
    @Module
    class TestAppModule
}