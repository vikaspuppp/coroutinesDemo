package com.example.basics.countryModule.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name") var countryName: String,
    @SerializedName("flagPNG") var flag: String,
    @SerializedName("capital") var capital: String
) {
}