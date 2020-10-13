package de.brainlezz.poe.models.poe

data class SocketedItem(
    val additionalProperties: List<AdditionalPropertyX>,
    val colour: String,
    val descrText: String,
    val explicitMods: List<String>,
    val frameType: Int,
    val h: Int,
    val icon: String,
    val id: String,
    val identified: Boolean,
    val ilvl: Int,
    val name: String,
    val properties: List<PropertyXX>,
    val requirements: List<RequirementX>,
    val secDescrText: String,
    val socket: Int,
    val support: Boolean,
    val typeLine: String,
    val verified: Boolean,
    val w: Int
)