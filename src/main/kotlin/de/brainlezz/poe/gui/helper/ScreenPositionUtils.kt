package de.brainlezz.poe.gui.helper

import de.brainlezz.poe.models.poe.Item

class ScreenPositionUtils {

    companion object {
        private val tab_positions = mapOf("FULLHD" to arrayOf(17, 160, 651, 793),
                "QHD" to arrayOf(23, 216, 864, 1057))

        val resolution = "QHD"

        var isQuad = true

        public fun tab_pos_end_x(): Int {
            return tab_positions[resolution]?.get(2)!!
        }

        public fun tab_pos_start_x(): Int {
            return tab_positions[resolution]?.get(0)!!
        }

        public fun tab_pos_end_y(): Int {
            return tab_positions[resolution]?.get(3)!!
        }

        public fun tab_pos_start_y(): Int {
            return tab_positions[resolution]?.get(1)!!
        }

        public fun boxWidth(): Double {
            val div = if (isQuad) 24.0 else 12.0
            return (tab_pos_end_x() - tab_pos_start_x()).div(div)
        }

        public fun boxHeight(): Double {
            val div = if (isQuad) 24.0 else 12.0
            return (tab_pos_end_y() - tab_pos_start_y()).div(div)
        }

        public fun getItemScreenPos(item: Item): Pair<Double, Double> {
            return Pair(tab_pos_start_x() + (item.x * boxWidth()), tab_pos_start_y() + (item.y * boxHeight()))
        }

        public fun getItemSize(item: Item): Pair<Double, Double> {
            return Pair(item.w * boxWidth(), item.h * boxHeight())
        }
    }
}