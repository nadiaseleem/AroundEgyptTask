package com.example.aroundegypt.common.di

import android.content.Context
import android.util.Log
import com.example.aroundegypt.BuildConfig
import com.example.aroundegypt.common.data.internet_connection_Handler.InternetConnectivityChecker
import com.example.aroundegypt.common.data.repository.remote.AroundEgyptApiService
import com.example.aroundegypt.common.data.repository.remote.RetrofitRestApiNetworkProvider
import com.example.aroundegypt.common.domain.internet_connection_handler.IInternetConnectivityChecker
import com.example.aroundegypt.common.domain.repository.remote.IRestApiNetworkProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private const val CONNECT_TIMEOUT = 15L
    private const val MAX_IDLE_CONNECTIONS = 10
    private const val KEEP_ALIVE_DURATION = 10L
    private const val LOG_TAG = "Network"

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideRestApiNetworkProvider(apiService: AroundEgyptApiService): IRestApiNetworkProvider =
        RetrofitRestApiNetworkProvider(apiService)

    @Provides
    @Singleton
    fun provideAroundEgyptApiService(retrofit: Retrofit): AroundEgyptApiService =
        retrofit.create(AroundEgyptApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .connectionPool(
                ConnectionPool(
                    MAX_IDLE_CONNECTIONS,
                    KEEP_ALIVE_DURATION,
                    TimeUnit.MINUTES
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Log.d(LOG_TAG, message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    fun provideInternetConnectivityChecker(@ApplicationContext context: Context): IInternetConnectivityChecker {
        return InternetConnectivityChecker(context)
    }
}