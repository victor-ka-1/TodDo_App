package com.kasko.victor.testapp.toddoapp.api.models

import androidx.room.Embedded
import androidx.room.Entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class UserRemote(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("username") val usermane : String,
    @SerializedName("email") val email :String,
    @SerializedName("address") val address: AddressRemote,
    @SerializedName("phone") val phone :String,
    @SerializedName("website") val website : String,
    @SerializedName("company") val company : CompanyRemote
)
