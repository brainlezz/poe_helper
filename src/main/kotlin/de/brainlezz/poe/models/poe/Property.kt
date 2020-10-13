package de.brainlezz.poe.models.poe

data class Property(
    val displayMode: Int,
    val name: String,
    val values: List<List<Any>>
)