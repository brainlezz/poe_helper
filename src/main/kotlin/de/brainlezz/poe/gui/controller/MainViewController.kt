package de.brainlezz.poe.gui.controller

import de.brainlezz.poe.gui.helper.ItemHighlightHelper
import de.brainlezz.poe.helpers.NinjaUtils
import de.brainlezz.poe.models.ninja.Line
import de.brainlezz.poe.models.poe.Item
import de.brainlezz.poe.services.RemoteDataProvider
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.HBox

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

}