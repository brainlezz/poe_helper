package de.brainlezz.poe.models.ninja

data class Line(
    val chaosEquivalent: Double,
    val currencyTypeName: String,
    val detailsId: String,
    val lowConfidencePaySparkLine: LowConfidencePaySparkLine,
    val lowConfidenceReceiveSparkLine: LowConfidenceReceiveSparkLine,
    val pay: Pay,
    val paySparkLine: PaySparkLine,
    val receive: Receive,
    val receiveSparkLine: ReceiveSparkLine
)