package de.brainlezz.poe.gui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.URL


class PoeHelperApp : Application() {
    companion object{
        val HEIGHT = 400.0
        val WIDTH = 980.0

    }
    override fun start(primaryStage: Stage?) {
        var root : Parent = FXMLLoader.load<Parent>(this.javaClass.getResource("/MainView.fxml"))
        var scene = Scene(root, WIDTH, HEIGHT)

        primaryStage?.title = "test window"
        primaryStage?.scene = scene
       // primaryStage?.sizeToScene()
        //primaryStage?.isResizable = false
        primaryStage?.show()
    }

}