package de.brainlezz.poe.models.poe

data class AdditionalPropertyX(
    val displayMode: Int,
    val name: String,
    val progress: Double,
    val type: Int,
    val values: List<List<Any>>
)