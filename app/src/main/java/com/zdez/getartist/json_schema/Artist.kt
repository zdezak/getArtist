package com.zdez.getartist.json_schema

import com.squareup.moshi.Json
import com.zdez.getartist.Image

data class Artist(
    @Json(name = "name")
    var name: String,

    @Json(name = "listeners")
    var listeners: String,

    @Json(name = "mbid")
    var id: String,

    @Json(name = "url")
    var url: String,

    @Json(name = "streamable")
    var streamable: String,

    @Json(name = "image")
    var image: List<Image>
)