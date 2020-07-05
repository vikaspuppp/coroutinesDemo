package com.example.basics.countryModule.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryServices {
    private val URL = "https://raw.githubusercontent.com/"

    fun getCountryServices(): CountryApi =
        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(CountryApi::class.java)
}