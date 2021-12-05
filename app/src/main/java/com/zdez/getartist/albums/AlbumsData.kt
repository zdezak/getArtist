package com.zdez.getartist.albums

import com.squareup.moshi.Json

data class Album(
    @Json(name = "name")
    val name: String,

    @Json(name = "playcount")
    val playCount: Int,

    @Json(name = "mbid")
    val mbid: String?,

    @Json(name = "url")
    val url: String,

    @Json(name = "artist")
    val artist: Artist,

    @Json(name = "image")
    val images: List<Image>
)

data class Artist(
    @Json(name = "name")
    val name: String,

    @Json(name = "mbid")
    val mbid: String,

    @Json(name = "url")
    val url: String
)

data class Attr(
    @Json(name = "artist")
    val artist: String,

    @Json(name = "page")
    val page: String,

    @Json(name = "perPage")
    val perPage: String,

    @Json(name = "totalPages")
    val totalPages: String,

    @Json(name = "total")
    val total: String
)

data class AlbumsData(
    @Json(name = "topalbums")
    val topAlbums: TopAlbums
)

data class Image(
    @Json(name = "#text")
    val text: String,

    @Json(name = "size")
    val size: String
)

data class TopAlbums(
    @Json(name = "album")
    val albums: List<Album>,

    @Json(name = "@attr")
    val attr: Attr
)