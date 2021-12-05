package com.zdez.getartist.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zdez.getartist.albums.AlbumsData
import com.zdez.getartist.main.ArtistData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://ws.audioscrobbler.com/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("2.0/")
    fun searchArtist(
        @Query("method") method: String = "artist.search",
        @Query("artist") artist: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String = "json"
    ): Call<ArtistData>

    @GET("2.0/?method=artist.getTopAlbums")
    fun searchAlbums(
        @Query("artist") artist: String,
        @Query("mbid") mbid: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String = "json"
    ): Call<AlbumsData>
}

object LastFMApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}