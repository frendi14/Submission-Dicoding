package com.alfatih.submissiondikoding.connection

import android.content.Context
import android.net.ConnectivityManager
import com.alfatih.submissiondikoding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Connection {

    private const val timeout = 60 //second

    fun open():ConnectionInterface{
        val gson: Gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit =  Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client(false, timeout))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(ConnectionInterface::class.java)
    }

    private fun client(retry: Boolean, duration: Int): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .connectTimeout(duration.toLong(), TimeUnit.SECONDS)
                .readTimeout(duration.toLong(), TimeUnit.SECONDS)
                .writeTimeout(duration.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(retry)
        if(BuildConfig.DEBUG){
            builder.addNetworkInterceptor(
                    HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    fun checkHttpCode(httpCode: Int): Boolean = httpCode == 200

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}