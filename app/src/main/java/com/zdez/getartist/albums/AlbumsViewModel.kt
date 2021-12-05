package com.zdez.getartist.albums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.getartist.api.LastFMApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel(val api_key: String, val artistId: String, val artist: String) : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {
        LastFMApi.retrofitService.searchAlbums(artist = artist,id = artistId, api_key = api_key)
            .enqueue(object : Callback<AlbumsData> {
                override fun onResponse(
                    call: Call<AlbumsData>,
                    response: Response<AlbumsData>
                ) {
                    _albums.value = response.body()?.topAlbums?.albums
                }

                override fun onFailure(call: Call<AlbumsData>, t: Throwable) {
                    _albums.value = listOf()
                }
            }
            )
    }
}

class AlbumsViewModelFactory(
    private val api_key: String,
    private val artistId: String,
    private val artistName: String
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(api_key, artistId, artistName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}