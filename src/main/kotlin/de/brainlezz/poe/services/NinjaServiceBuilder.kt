package de.brainlezz.poe.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NinjaServiceBuilder {
    private const val URL ="https://poe.ninja/"
    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder()

    val gsonBuilder  =  GsonBuilder().addDeserializationExclusionStrategy(MissingFieldDetection())

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        .client(okHttp.build())

    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface


    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }
}

