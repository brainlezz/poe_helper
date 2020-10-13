package de.brainlezz.poe.models.poe

data class Hybrid(
    val baseTypeName: String,
    val explicitMods: List<String>,
    val isVaalGem: Boolean,
    val properties: List<Property>,
    val secDescrText: String
)