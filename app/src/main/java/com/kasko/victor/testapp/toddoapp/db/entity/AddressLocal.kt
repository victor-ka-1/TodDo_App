package com.kasko.victor.testapp.toddoapp.db.entity

import androidx.room.Embedded
import java.io.Serializable

data class AddressLocal(
    val street : String,
    val suite : String,
    val city :String,
    val zipCode : String,
    @Embedded(prefix = "geoPos")
    val geoPosition: GeoPositionLocal
) : Serializable
