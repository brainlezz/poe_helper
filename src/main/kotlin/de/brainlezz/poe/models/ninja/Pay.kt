package de.brainlezz.poe.models.ninja

data class Pay(
    val count: Int,
    val data_point_count: Int,
    val get_currency_id: Int,
    val id: Int,
    val includes_secondary: Boolean,
    val league_id: Int,
    val pay_currency_id: Int,
    val sample_time_utc: String,
    val value: Double
)