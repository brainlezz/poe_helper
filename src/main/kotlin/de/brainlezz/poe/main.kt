package de.brainlezz.poe

import de.brainlezz.poe.gui.PoeHelperApp
import de.brainlezz.poe.services.*
import javafx.application.Application.*


fun main(args : Array<String>){
    //launch<PoeHelperApp>(args)
    launch(PoeHelperApp::class.java, *args)
}

fun loadStuff(){
    RemoteDataProvider.getItemsFromStash(1){
        it.forEach { it -> println(if (it.name.isEmpty()) it.typeLine else it.name) }
    }

    RemoteDataProvider.getCurrencyOverview {
        it.currencyDetails.forEach { it -> println(it.name) }
    }

    readLine()

}
