package de.brainlezz.poe.services

import de.brainlezz.poe.models.ninja.NinjaCurrency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NinjaCurrencyService {
    @GET("/api/data/currencyoverview")
    fun getAffectedCountryList (@Query("league") league : String,
                                @Query("type") type : String) : Call<NinjaCurrency>

}