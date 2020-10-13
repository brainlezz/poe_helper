package de.brainlezz.poe.gui.controller

import de.brainlezz.poe.helpers.NinjaUtils
import de.brainlezz.poe.models.ninja.Line
import de.brainlezz.poe.services.RemoteDataProvider
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView

class CurrencyOverviewController {

    @FXML
    private lateinit var currencyOverviewTable : TableView<Line>

    @FXML
    private lateinit var currencyNameColumn : TableColumn<Line, String>

    @FXML
    private lateinit var currencyValuePayColumn : TableColumn<Line, String>

    @FXML
    private lateinit var currencyValueReceiveColumn : TableColumn<Line, String>

    private var lineData = FXCollections.observableArrayList<Line>()

    private var loadCurrencyData = {
        RemoteDataProvider.getCurrencyOverview {
            lineData.clear()
            lineData.addAll(it.lines)
        }
    }

    @FXML
    fun initialize() {
        currencyNameColumn.setCellValueFactory { SimpleStringProperty(it.value.currencyTypeName) }
        currencyValuePayColumn.setCellValueFactory {
            SimpleStringProperty(if (it.value.pay == null) "N/A" else NinjaUtils.round(1.0 / it.value.pay.value, 2).toString() + " Chaos")
        }
        currencyValueReceiveColumn.setCellValueFactory {
            SimpleStringProperty(if (it.value.receive == null) "N/A" else NinjaUtils.round(it.value.receive.value, 2).toString() + " Chaos")
        }

        currencyOverviewTable.items = lineData
        loadCurrencyData()
    }

    fun onReloadClicked(actionEvent: ActionEvent) {
        loadCurrencyData()
    }
}