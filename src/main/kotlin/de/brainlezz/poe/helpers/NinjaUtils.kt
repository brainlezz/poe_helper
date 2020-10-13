package de.brainlezz.poe.helpers

import de.brainlezz.poe.models.ninja.CurrencyDetail
import de.brainlezz.poe.models.ninja.NinjaCurrency
import kotlin.math.pow

class NinjaUtils {
    companion object{
        fun round(number : Double, digits : Int) : Double{
            var factor = 10.0.pow(digits)
            return (number * factor).toInt().toDouble() / factor
        }
    }
}