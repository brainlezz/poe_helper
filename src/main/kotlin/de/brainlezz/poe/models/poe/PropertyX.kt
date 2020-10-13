package de.brainlezz.poe.models.poe

data class PropertyX(
    val displayMode: Int,
    val name: String,
    val type: Int,
    val values: List<List<Any>>
)