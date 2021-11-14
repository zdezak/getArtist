package com.zdez.getartist.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.getartist.API.Api
import com.zdez.getartist.API.Artist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api_key: String): ViewModel() {

    private val _navigateToAlbums = MutableLiveData<Boolean>()
    val navigateToAlbums:LiveData<Boolean>
        get() = _navigateToAlbums

    private val _artist = MutableLiveData<List<Artist>>()
    val artist: LiveData<List<Artist>>
        get() = _artist

    init {
        _navigateToAlbums.value = false
    }

    fun getArtist(artist: String) {
        Api.retrofitService.searchArtist(artist,api_key).enqueue(object: Callback<List<Artist>> {
            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                _artist.value = response.body()
            }

            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                _artist.value?.get(0)?.name = "Failure: ${t.message}"
            }
        })
    }

    fun onArtistClicked(mbid: String) {
        _navigateToAlbums.value = true
    }
}

class MainViewModelFactory(
    private val api_key: String
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(api_key) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}