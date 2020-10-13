package de.brainlezz.poe.gui.helper

import de.brainlezz.poe.gui.overlay.ItemHightlightWindow
import de.brainlezz.poe.models.poe.Item
import javafx.scene.control.Label
import javafx.scene.paint.Color

object ItemHighlightHelper {

    var chaosSetLabel: Label? = null

    private var currentHighlightIndex = 0

    private val overlayWindows = mutableListOf<ItemHightlightWindow>()

    private var colorPalette : List<Color> = listOf(
            Color.ALICEBLUE, Color.DEEPSKYBLUE, Color.CRIMSON,
            Color.BLUEVIOLET, Color.DARKOLIVEGREEN, Color.CYAN,
            Color.ORANGE, Color.YELLOW, Color.NAVY,
            Color.IVORY, Color.YELLOWGREEN, Color.DARKGOLDENROD,
            Color.MEDIUMORCHID, Color.LIGHTSALMON, Color.DARKSLATEGREY
    )

    var completeChaosRecipes : List<List<Item>> = listOf()
        set(value) {
            field = value
            currentHighlightIndex = 0
            updateLabel()

        }

    private fun updateLabel() {
        if(currentHighlightIndex == 0)
            chaosSetLabel?.text = "No Set Highlighted"
        else
            chaosSetLabel?.text = "Chaos Set: ${currentHighlightIndex}/${completeChaosRecipes.size}"
    }


    private fun addShowItemWindow(item : Item, color : Color){
        var window = ItemHightlightWindow(item, color)
        overlayWindows.add(window)
        window.show()
    }

    fun highlightAll(){
        var i = 0
        completeChaosRecipes.forEach {
            it.forEach { item ->
                addShowItemWindow(item, colorPalette[i])
            }
            i++
        }
    }

    fun highlightNext(){
        if(completeChaosRecipes.size > currentHighlightIndex){
            completeChaosRecipes[currentHighlightIndex].forEach { item ->
                addShowItemWindow(item, colorPalette[currentHighlightIndex])
            }
            currentHighlightIndex++
            updateLabel()
        }else{
            println("no chaos recipe to highlight")
        }
    }

    fun removeHighlights(){
        overlayWindows.forEach {
            //it.performClick()
            it.hide()
        }
        overlayWindows.clear()
    }

}