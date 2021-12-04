package com.zdez.getartist

import com.squareup.moshi.Json
import com.zdez.getartist.json_schema.Artist

data class Artistmatches(
    @Json(name = "artist")
    var artist: List<Artist>
)