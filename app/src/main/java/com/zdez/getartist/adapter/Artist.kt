package com.zdez.getartist.adapter

import com.squareup.moshi.Json

data class Artist(
    var name: String,
    @Json(name = "mbid") val id: String,
    val url: String,
    @Json(name = "image_small") val imageSmall: String,
    val streamable: Int
)