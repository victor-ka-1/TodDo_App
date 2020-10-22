package com.kasko.victor.testapp.toddoapp.api.models

import com.google.gson.annotations.SerializedName

data class CompanyRemote(
    @SerializedName("name") val companyName : String,
    @SerializedName("catchPhrase") val catchPhrase : String,
    @SerializedName("bs") val bs : String
)
