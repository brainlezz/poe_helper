package de.brainlezz.poe.gui.controller

import de.brainlezz.poe.gui.helper.ItemHighlightHelper
import de.brainlezz.poe.gui.overlay.ItemHightlightWindow
import de.brainlezz.poe.gui.settings.SettingsController
import de.brainlezz.poe.models.poe.Item
import de.brainlezz.poe.services.RemoteDataProvider
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.paint.Color
import tornadofx.Controller
import kotlin.random.Random

class ChaosReceipeController() : Controller() {

    companion object{
        val ILVL_CHAOS_MIN = 60
        val ILVL_CHAOS_MAX = 74
    }

    val itemTypeRegex = "2DItems\\/(Armours\\/|Weapons\\/)?([^\\/]*)".toRegex()

    @FXML
    private lateinit var labelAmuletsToHigh : Label
    @FXML
    private lateinit var labelArmourToHigh : Label
    @FXML
    private lateinit var labelBeltToHigh : Label
    @FXML
    private lateinit var labelBootsToHigh : Label
    @FXML
    private lateinit var labelGlovesToHigh : Label
    @FXML
    private lateinit var labelHelmetToHigh : Label
    @FXML
    private lateinit var labelRingToHigh : Label
    @FXML
    private lateinit var labelWeaponToHigh : Label
    @FXML
    private lateinit var labelAmuletsPerfect : Label
    @FXML
    private lateinit var labelArmourPerfect : Label
    @FXML
    private lateinit var labelBeltPerfect : Label
    @FXML
    private lateinit var labelBootsPerfect : Label
    @FXML
    private lateinit var labelGlovesPerfect : Label
    @FXML
    private lateinit var labelHelmetPerfect : Label
    @FXML
    private lateinit var labelRingPerfect : Label
    @FXML
    private lateinit var labelWeaponPerfect : Label
    @FXML
    private lateinit var labelAmuletsTotal : Label
    @FXML
    private lateinit var labelArmourTotal : Label
    @FXML
    private lateinit var labelBeltTotal : Label
    @FXML
    private lateinit var labelBootsTotal : Label
    @FXML
    private lateinit var labelGlovesTotal : Label
    @FXML
    private lateinit var labelHelmetTotal : Label
    @FXML
    private lateinit var labelRingTotal : Label
    @FXML
    private lateinit var labelWeaponTotal : Label

    private var itemMap : MutableMap<ItemType, MutableList<Item>> = mutableMapOf()

    private lateinit var countingMap : Map<ItemType, Array<Long>>

    private lateinit var labelMap : Map<ItemType, Array<Label>>

    private var overlayWindows = mutableListOf<ItemHightlightWindow>()

    private var itemList = mutableListOf<Item>()

    private var chaosReceipePosibilities = mutableListOf<List<Item>>()

    @FXML
    fun initialize(){
        labelMap = mapOf(
                ItemType.BOOTS to arrayOf(labelBootsToHigh, labelBootsPerfect, labelBootsTotal),
                ItemType.RINGS to arrayOf(labelRingToHigh, labelRingPerfect, labelRingTotal),
                ItemType.HELMETS to arrayOf(labelHelmetToHigh, labelHelmetPerfect, labelHelmetTotal),
                ItemType.GLOVES to arrayOf(labelGlovesToHigh, labelGlovesPerfect, labelGlovesTotal),
                ItemType.BELTS to arrayOf(labelBeltToHigh, labelBeltPerfect, labelBeltTotal),
                ItemType.WEAPONS to arrayOf(labelWeaponToHigh, labelWeaponPerfect, labelWeaponTotal),
                ItemType.BODYARMOURS to arrayOf(labelArmourToHigh, labelArmourPerfect, labelArmourTotal),
                ItemType.AMULETS to arrayOf(labelAmuletsToHigh, labelAmuletsPerfect, labelAmuletsTotal)
        )

        initCountingMap()

    }

    private fun initCountingMap(){
        countingMap = mapOf(
                ItemType.BOOTS to arrayOf(0,0,0),
                ItemType.RINGS to arrayOf(0,0,0),
                ItemType.HELMETS to arrayOf(0,0,0),
                ItemType.GLOVES to arrayOf(0,0,0),
                ItemType.BELTS to arrayOf(0,0,0),
                ItemType.WEAPONS to arrayOf(0,0,0),
                ItemType.BODYARMOURS to arrayOf(0,0,0),
                ItemType.AMULETS to arrayOf(0,0,0))
    }

    private fun updateViews(){
        labelMap.forEach{ entry ->
            var (itemType, labels) = entry
            var items = itemMap[itemType]
            var toHigh = items?.sumOf{item -> if(item.ilvl > ILVL_CHAOS_MAX) 1.toLong() else 0.toLong()} ?: 0
            var perfect = items?.sumOf{item -> if(item.ilvl in ILVL_CHAOS_MIN..ILVL_CHAOS_MAX) 1.toLong() else 0.toLong()} ?: 0

            labels[0].text = toHigh.toString()
            labels[1].text = perfect.toString()
            var total = toHigh?.plus(perfect ?: 0)
            if(itemType != ItemType.WEAPONS)
                labels[2].text = total?.toString()

            else{
                val validItems = items?.filter { it.ilvl >= ILVL_CHAOS_MIN }
                labels[2].text = "${toHigh?.plus(perfect ?: 0)} (" +
                        "${validItems?.count { getItemTypeString(it).equals("ONEHANDWEAPONS") }} OH)"
            }

            toHigh?.let { countingMap[itemType]?.set(0, it) };
            perfect?.let { countingMap[itemType]?.set(1, it) };
            total?.let { countingMap[itemType]?.set(2, it) }


        }
    }

    private fun calculateChaosRecipe(){
        chaosReceipePosibilities.clear()
        do {
            var foundAll = true
            var chaosRecipe = mutableListOf<Item>()
            // look for the highest count of items that can be used for the recipe
            var maxOccuring = 0
            var maxOccuringType : ItemType? = null
            countingMap.forEach { (t, u) ->
                if (u[1] > maxOccuring) {
                    maxOccuring = u[1].toInt()
                    maxOccuringType = t
                }
            }
            if(maxOccuring == 0){
                println("no items left for chaos recipe")
                break
            }

            var perfectILvlFound = false
            // look for every itemType if a possible item is available
            countingMap.forEach { (t, u) ->
                var match: Item? = null
                // we need two rings and two weapons TODO: two handed weapons
                var repeats = if (t == ItemType.WEAPONS || t == ItemType.RINGS) 2 else 1
                for (i in 1..repeats) {
                    // look for an item with a higher item level then needed to only use necessary
                    // items in the required ilvl range for the chaos recipe
                    match = itemMap[t]?.find { item -> item.ilvl > ILVL_CHAOS_MAX }
                    // if no item with an higher item level was found or if we are looking for the itemType we have
                    // the most items in ilvl range look for a item in chaos recipe range
                    if (match == null || (t == maxOccuringType && !perfectILvlFound)) {
                        match = itemMap[t]?.find { item -> item.ilvl in ILVL_CHAOS_MIN..ILVL_CHAOS_MAX }
                        if(match != null)
                            perfectILvlFound = true
                    }
                    // no suited item was found for itemType
                    if (match == null) {
                        foundAll = false
                    } else {
                        // found a suiting item, remove it from the list to prevent multiple usage
                        chaosRecipe.add(match)
                        itemMap[t]?.remove(match)
                    }

                }

            }
            if(foundAll)
                chaosReceipePosibilities.add(chaosRecipe)
        } while (foundAll)

        ItemHighlightHelper.completeChaosRecipes = chaosReceipePosibilities
    }

    private var addItemToMap : (Item) -> Unit = { item ->
        if (!item.identified) {
            var type = getItemType(item)
            if (!itemMap.containsKey(type))
                itemMap[type] = mutableListOf(item)
            else
                itemMap[type]?.add(item)
        }
    }

    private var printItemMap = {
        itemMap.forEach {
            println(it.key)
            it.value.forEach{
                println("\t -> ${it.typeLine}")
            }
        }
    }

    private var getItemType : (Item) -> ItemType = {item : Item ->
        var itemType = getItemTypeString(item)
        var returnValue = ItemType.OTHERS
        if(itemType != null)
            if(ItemType.values().any { it.name == itemType})
                returnValue = ItemType.valueOf(itemType)
            else if (listOf("TWOHANDWEAPONS", "ONEHANDWEAPONS").any{it.equals(itemType)})
                returnValue = ItemType.WEAPONS
        returnValue
    }

    private var getItemTypeString = { item : Item -> itemTypeRegex.find(item.icon)?.groups?.get(2)?.value?.toUpperCase()}

    fun loadTab(){

        RemoteDataProvider.getItemsFromStash(
                SettingsController.settings.tabIndex) { items ->
            itemMap.clear()
            items.forEach { addItemToMap(it)}
            printItemMap()
            Platform.runLater {
                initCountingMap()
                updateViews()
                calculateChaosRecipe()
            }
        }
    }
}

enum class ItemType{
    BOOTS, RINGS, HELMETS, GLOVES, BELTS, WEAPONS, BODYARMOURS, AMULETS, OTHERS
}