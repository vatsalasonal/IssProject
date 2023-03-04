package com.issapp

//import com.audhil.medium.gweatherapp.GDelegate
//import com.audhil.medium.gweatherapp.data.remote.AppAPIs
//import com.audhil.medium.gweatherapp.repository.AppRepository
//import io.mockk.mockk

/*
@InstallIn(SingletonComponent::class)
@Module
class TestAppModule : AppModule() {

    @Provides
    override fun getNetwork(retroFit: Retrofit): Network {
        return retroFit.create(Network::class.java)
    }

    @Provides
    override fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    override fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    override fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }
}
*/
