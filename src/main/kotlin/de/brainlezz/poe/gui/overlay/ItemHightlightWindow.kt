package de.brainlezz.poe.gui.overlay

import de.brainlezz.poe.models.poe.Item
import javafx.event.Event
import javafx.event.EventHandler
import javafx.geometry.Bounds
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.awt.Robot
import java.awt.event.InputEvent

class ItemHightlightWindow (var item : Item, color : Color) : Parent() {

    private val stage  = Stage()

    companion object{
        private val robot = Robot()

        private val tab_positions = mapOf("FULLHD" to arrayOf(17,160,651,793),
                                          "QHD" to arrayOf(23,216,864,1057))

        val resolution = "QHD"

        var isQuad = true

        fun tab_pos_end_x() : Int {
            return tab_positions[resolution]?.get(2)!!
        }

        fun tab_pos_start_x() : Int {
            return tab_positions[resolution]?.get(0)!!
        }

        fun tab_pos_end_y() : Int {
            return tab_positions[resolution]?.get(3)!!
        }

        fun tab_pos_start_y() : Int {
            return tab_positions[resolution]?.get(1)!!
        }

        fun boxWidth() : Double{
            val div = if(isQuad) 24.0 else 12.0
            return (tab_pos_end_x() - tab_pos_start_x()).div(div)
        }

        fun boxHeight() : Double{
            val div = if(isQuad) 24.0 else 12.0
            return (tab_pos_end_y() - tab_pos_start_y()).div(div)
        }

    }

    init {

        var x = tab_pos_start_x() + (item.x * boxWidth())
        var y = tab_pos_start_y() + (item.y * boxHeight())

        var w = item.w * boxWidth()
        var h = item.h * boxHeight()

        var scene = Scene(this, w, h, color)
        stage.x = x
        stage.y = y
        stage.scene = scene
        stage.initStyle(StageStyle.UNDECORATED)
        stage.isAlwaysOnTop = true
        stage.opacity = 0.5
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, EventHandler {
            hide()
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        })
    }


    fun show() = stage.show()

    fun performClick(){

        robot.mouseMove((scene.window.x + 3).toInt(), (scene.window.y + 3).toInt())
        Event.fireEvent(stage, MouseEvent(MouseEvent.MOUSE_CLICKED, 0.0,
                0.0, 0.0, 0.0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null))
    }

    fun hide() = stage.close()
}