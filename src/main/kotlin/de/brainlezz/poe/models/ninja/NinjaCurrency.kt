package de.brainlezz.poe.models.ninja

data class NinjaCurrency(
    val currencyDetails: List<CurrencyDetail>,
    val language: Language,
    val lines: List<Line>
)