package de.brainlezz.poe.models.poe

data class StashItems(
    val items: List<Item>,
    val numTabs: Int,
    val quadLayout: Boolean
)