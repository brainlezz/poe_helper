package de.brainlezz.poe.models.poe

data class Item(
    val abyssJewel: Boolean,
    val additionalProperties: List<AdditionalProperty>,
    val artFilename: String,
    val corrupted: Boolean,
    val craftedMods: List<String>,
    val descrText: String,
    val enchantMods: List<String>,
    val explicitMods: List<String>,
    val flavourText: List<String>,
    val frameType: Int,
    val h: Int,
    val hybrid: Hybrid,
    val icon: String,
    val id: String,
    val identified: Boolean,
    val ilvl: Int,
    val implicitMods: List<String>,
    val inventoryId: String,
    val itemLevel: Int,
    val league: String,
    val maxStackSize: Int,
    val name: String,
    val properties: List<PropertyX>,
    val requirements: List<Requirement>,
    val secDescrText: String,
    val socketedItems: List<SocketedItem>,
    val sockets: List<Socket>,
    val stackSize: Int,
    val support: Boolean,
    val typeLine: String,
    val utilityMods: List<String>,
    val veiled: Boolean,
    val veiledMods: List<String>,
    val verified: Boolean,
    val w: Int,
    val x: Int,
    val y: Int
)