package com.zdez.getartist

import com.squareup.moshi.Json

class Attr(
    @Json(name = "for")
    var `for`: String
)