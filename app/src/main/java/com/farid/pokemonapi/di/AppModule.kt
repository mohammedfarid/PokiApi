package com.farid.pokemonapi.di


import android.content.Context
import com.farid.pokemonapi.PokemonApp
import com.farid.pokemonapi.data.remote.APIs
import com.farid.pokemonapi.common.Constants.BASE_API
import com.farid.pokemonapi.data.repository.MainRepositoryImp
import com.farid.pokemonapi.domain.repository.MainRepository
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): PokemonApp {
        return app as PokemonApp
    }

    @Provides
    @Singleton
    fun provideAPIs(client: OkHttpClient): APIs {
        return Retrofit.Builder()
            .baseUrl(BASE_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(APIs::class.java)
    }

    private const val READ_TIMEOUT = 5
    private const val WRITE_TIMEOUT = 5
    private const val CONNECTION_TIMEOUT = 5

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MINUTES)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.MINUTES)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            requestBuilder.addHeader("Accept", "application/json")
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideMainRepository(api: APIs): MainRepository {
        return MainRepositoryImp(api)
    }
}