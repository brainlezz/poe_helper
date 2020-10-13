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

        val TAB_POS_START_X = 19
        val TAB_POS_START_Y = 162
        val TAB_POS_END_X = 651
        val TAB_POS_END_Y = 793

        var isQuad = true

        fun boxWidth() : Double{
            val div = if(isQuad) 24.0 else 12.0
            return (TAB_POS_END_X - TAB_POS_START_X).div(div)
        }

        fun boxHeight() : Double{
            val div = if(isQuad) 24.0 else 12.0
            return (TAB_POS_END_Y - TAB_POS_START_Y).div(div)
        }

    }

    init {

        var x = TAB_POS_START_X + (item.x * boxWidth())
        var y = TAB_POS_START_Y + (item.y * boxHeight())

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