package com.zdez.getartist.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zdez.getartist.adapter.Artist
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query


private const val BASE_URL = "http://ws.audioscrobbler.com/2.0/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    //Post method need?
    @GET("/?")
    fun searchArtist(
        @Query("method") method: String = "artist.search",
        @Query("artist") artist: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String = "json"
    ): Call<List<Artist>>
}

object LastFMApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}