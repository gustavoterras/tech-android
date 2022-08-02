package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig.BASE_API_URL
import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.data.PicPayServiceHelper
import com.picpay.desafio.android.data.PicPayServiceHelperImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl(): String = BASE_API_URL

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: PicPayServiceHelperImpl): PicPayServiceHelper = apiHelper
}