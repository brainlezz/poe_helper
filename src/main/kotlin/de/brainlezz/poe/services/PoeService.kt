package de.brainlezz.poe.services

import de.brainlezz.poe.models.ninja.NinjaCurrency
import de.brainlezz.poe.models.poe.StashItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PoeService {

    @GET("character-window/get-stash-items")
    fun getTabContent (@Query("league") league : String,
                                @Query("realm") realm : String,
                                @Query("accountName") accountName : String,
                                @Query("tabIndex") tabIndex : Int,
                                @Header("Cookie") sessionId : String) : Call<StashItems>
}

