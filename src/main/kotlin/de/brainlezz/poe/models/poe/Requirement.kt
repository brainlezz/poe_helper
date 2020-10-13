package de.brainlezz.poe.models.poe

data class Requirement(
    val displayMode: Int,
    val name: String,
    val suffix: String,
    val values: List<List<Any>>
)