package com.zdez.getartist

import com.squareup.moshi.Json


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
