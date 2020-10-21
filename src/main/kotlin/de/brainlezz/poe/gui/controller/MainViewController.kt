package de.brainlezz.poe.gui.controller

import de.brainlezz.poe.gui.helper.ItemHighlightHelper
import de.brainlezz.poe.gui.settings.SettingsController
import de.brainlezz.poe.helpers.NinjaUtils
import de.brainlezz.poe.models.ninja.Line
import de.brainlezz.poe.models.poe.Item
import de.brainlezz.poe.services.RemoteDataProvider
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.HBox
import javafx.stage.Stage

class MainViewController {

    @FXML
    private lateinit var chaosRecipeController: ChaosReceipeController

    @FXML
    private lateinit var labelChaosSets : Label

    @FXML
    fun initialize(){
        ItemHighlightHelper.chaosSetLabel = labelChaosSets
        chaosRecipeController.loadTab()
    }

    fun highlightChaosSet(actionEvent: ActionEvent) {
        ItemHighlightHelper.highlightNext()
    }

    fun removeItemHighlights(actionEvent: ActionEvent) {
        ItemHighlightHelper.removeHighlights()
    }

    fun reloadChaosTab(actionEvent: ActionEvent) {
        ItemHighlightHelper.removeHighlights()
        chaosRecipeController.loadTab()
    }

    fun openSettings(actionEvent: ActionEvent) {
        var loader = FXMLLoader(this.javaClass.getResource("/Settings.fxml"))
        var root : Parent = loader.load<Parent>()
        var stage = Stage()
        loader.getController<SettingsController>().stage = stage
        stage.title = "Settings"
        stage.scene = Scene(root, 600.0,400.0)
        stage.show()
    }

    fun fillInventory(actionEvent: ActionEvent) {
        chaosRecipeController.fillInventory()
    }

}