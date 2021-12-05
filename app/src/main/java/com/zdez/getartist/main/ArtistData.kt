package com.zdez.getartist.main

import com.squareup.moshi.Json

data class ArtistData(
    @Json(name = "results")
    val results: Results
)

data class Results(

    @Json(name = "opensearch:Query")
    var query: OpensearchQuery,

    @Json(name = "opensearch:totalResults")
    var totalResults: String,

    @Json(name = "opensearch:startIndex")
    var startIndex: String,

    @Json(name = "opensearch:itemsPerPage")
    var itemsPerPage: String,

    @Json(name = "artistmatches")
    var artists: Artistmatches,

    @Json(name = "@attr")
    var attr: Attr
)

data class OpensearchQuery(
    @Json(name = "#text")
    var text: String,

    @Json(name = "role")
    var role: String,

    @Json(name = "searchTerms")
    var searchTerms: String,

    @Json(name = "startPage")
    var startPage: String
)

data class Artistmatches(
    @Json(name = "artist")
    var artist: List<Artist>
)
class Attr(
    @Json(name = "for")
    var `for`: String
)
class Image(
    @Json(name = "#text")
    var text: String,

    @Json(name = "size")
    var size: String
)

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
