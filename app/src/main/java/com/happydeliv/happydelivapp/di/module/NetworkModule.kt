package com.happydeliv.happydelivapp.di.module

import com.happydeliv.happydelivapp.BuildConfig
import com.happydeliv.happydelivapp.HappyDelivApp
import com.happydeliv.happydelivapp.api.NetworkService
import com.scoproject.newsapp.utils.AppUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
class NetworkModule(private val happyDelivApp: HappyDelivApp){

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpCacheDirectory = File(happyDelivApp.cacheDir, "httpCache")
        val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(rewriteResponseInterceptor)
                .addInterceptor(rewriteRresponseInterceptorOffline)
                .cache(cache)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    /**
     * Caching Network State
     * @Return Interceptor
     * */

    private val rewriteResponseInterceptor = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        val cacheControl = originalResponse?.header("Cache-Control")
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            originalResponse!!.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build()
        } else {
            originalResponse
        }
    }

    private val rewriteRresponseInterceptorOffline = Interceptor { chain ->
        var request = chain.request()
        var appUtils= AppUtils()
        if (!appUtils.isConnectingToInternet(happyDelivApp.applicationContext)) {
            request = request!!.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached")
                    .build()
        }
        chain.proceed(request)
    }

    /**
     * Provide Rest Client add base url  + set base client for network
     * @Return Retrofit
     * */

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }


    /**
     * Provide Network service
     * @Return Retrofit
     * */

    @Provides
    @Singleton
    fun provideNetworkService(restAdapter: Retrofit): NetworkService {
        return restAdapter.create(NetworkService::class.java)
    }
}