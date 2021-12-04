package com.zdez.getartist

import com.squareup.moshi.Json

class Image(
    @Json(name = "#text")
    var text: String,

    @Json(name = "size")
    var size: String
)