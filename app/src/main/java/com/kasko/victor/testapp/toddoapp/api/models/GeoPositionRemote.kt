package com.kasko.victor.testapp.toddoapp.api.models

import com.google.gson.annotations.SerializedName

data class GeoPositionRemote(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double
)