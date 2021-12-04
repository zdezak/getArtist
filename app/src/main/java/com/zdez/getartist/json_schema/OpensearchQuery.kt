package com.zdez.getartist

import com.squareup.moshi.Json

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