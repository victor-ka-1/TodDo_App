package com.kasko.victor.testapp.toddoapp.api.models

import com.google.gson.annotations.SerializedName


data class AddressRemote(
    @SerializedName("street") val street : String,
    @SerializedName("suite") val suite : String,
    @SerializedName("city") val city :String,
    @SerializedName("zipcode") val zipCode : String,
    @SerializedName("geo") val geoPosition: GeoPositionRemote
)