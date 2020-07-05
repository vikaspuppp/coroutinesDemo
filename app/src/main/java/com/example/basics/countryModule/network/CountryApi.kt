package com.example.basics.countryModule.network

import com.example.basics.countryModule.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountryList():Response<ArrayList<CountryModel>>
}