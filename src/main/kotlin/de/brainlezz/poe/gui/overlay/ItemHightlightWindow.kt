package de.brainlezz.poe.gui.overlay

import de.brainlezz.poe.gui.helper.ScreenPositionUtils
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
import java.awt.event.KeyEvent

class ItemHightlightWindow (var item : Item, color : Color) : Parent() {

    private val stage  = Stage()

    companion object{
        private val robot = Robot()
    }

    init {

        var (x, y) = ScreenPositionUtils.getItemScreenPos(item)
        var (w, h) = ScreenPositionUtils.getItemSize(item)

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