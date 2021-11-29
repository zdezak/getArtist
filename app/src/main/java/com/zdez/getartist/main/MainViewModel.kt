package com.zdez.getartist.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.getartist.api.LastFMApi
import com.zdez.getartist.adapter.Artist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api_key: String) : ViewModel() {

    private val _navigateToAlbums = MutableLiveData<String?>()
    val navigateToAlbums: LiveData<String?>
        get() = _navigateToAlbums

    private val _artist = MutableLiveData<List<Artist>>()
    val artist: LiveData<List<Artist>>
        get() = _artist

    init {
        _navigateToAlbums.value = null
    }

    fun getArtist(artist: String) {
        LastFMApi.retrofitService.searchArtist(artist = artist, api_key = api_key).enqueue(object : Callback<List<Artist>> {
            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                _artist.value = response.body()
            }

            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                _artist.value = listOf(Artist(
                    name = "Failure: ${t.message}",
                    "0",
                    "none",
                    "none",
                    0)
                )
            }
        })
    }

    fun onArtistClicked(id: String) {
        _navigateToAlbums.value = id
    }

    fun onNavigateToAlbumsCompleted(){
        _navigateToAlbums.value = null
    }
}

class MainViewModelFactory(
    private val api_key: String
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(api_key) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}