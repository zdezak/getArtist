package com.zdez.getartist.json_schema

import com.squareup.moshi.Json
import com.zdez.getartist.Results

data class JsonObjectFM(
    @Json(name = "results")
    val results: Results
)
