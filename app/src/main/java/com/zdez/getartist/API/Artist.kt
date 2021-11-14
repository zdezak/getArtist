package com.zdez.getartist.API

data class Artist(
    var name: String,
    val mbid: String,
    val url: String,
    val image_small: String,
    val streamable: Int
)