package de.brainlezz.poe.services

import de.brainlezz.poe.gui.settings.SettingsController
import de.brainlezz.poe.helpers.NinjaUtils
import de.brainlezz.poe.helpers.RetrofitUtils
import de.brainlezz.poe.models.ninja.NinjaCurrency
import de.brainlezz.poe.models.poe.Item
import de.brainlezz.poe.models.poe.StashItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteDataProvider {

    //private val sessionId = "c4c3a635311e4c57ceb6280ee2906a84"
    private val realm = "pc"
    //private val accountName = "cee_jay"
    //private val league = "Heist"

    private val poeService = PoeServiceBuilder.buildService(PoeService::class.java)
    private val ninjaService = NinjaServiceBuilder.buildService(NinjaCurrencyService::class.java)

    fun getItemsFromStash(tabIndex : Int, callback : (List<Item>) -> Unit){
        val poeRequest = poeService.getTabContent(SettingsController.settings.league,
                realm, SettingsController.settings.accountName, tabIndex,
            RetrofitUtils.createSessionCookie(SettingsController.settings.sessionId))
        enqueue(poeRequest){
            callback(it.items)
        }
    }

    fun getCurrencyOverview(callback : (NinjaCurrency) -> Unit) {
        val ninjaRequest = ninjaService.getAffectedCountryList(SettingsController.settings.league, "Currency")
        enqueue(ninjaRequest){
            callback(it)
        }
    }


    private fun <T> enqueue(request : Call<T>, callback: (T) -> Unit){
        request.enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(it) }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                println("request failed")
            }
        })
    }

}