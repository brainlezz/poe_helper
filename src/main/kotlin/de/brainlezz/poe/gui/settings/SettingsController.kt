package de.brainlezz.poe.gui.settings

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.io.*

class SettingsController {

    companion object{

        private var settingsFile = File("settings.json")

        private lateinit var gson : Gson
        private var settingsObjectType = object : TypeToken<SettingsObject>(){}.type
        public lateinit var settings : SettingsObject

        private fun saveSettings() = settingsFile.writeText(gson.toJson(settings))

        init {
            var fileExisted = true
            if(!settingsFile.exists()) {
                settingsFile.createNewFile()
                fileExisted = false
            }
            var json = settingsFile.readText()
            gson = Gson()

            var settingsTmp : SettingsObject? = gson.fromJson(json, settingsObjectType)
            settings = settingsTmp ?: SettingsObject()
            if(!fileExisted) saveSettings()
        }
    }
    @FXML
    private lateinit var txtAccountName : TextField
    @FXML
    private lateinit var txtLeague : TextField
    @FXML
    private lateinit var txtSessionID : TextField
    @FXML
    private lateinit var txtTabIndex : TextField

    var stage : Stage? = null


    @FXML
    private fun initialize() {
        txtAccountName.text = settings.accountName
        txtLeague.text = settings.league
        txtSessionID.text = settings.sessionId
        txtTabIndex.text = settings.tabIndex.toString()
    }

    private fun getSettingFieldInfo(){
        settings.accountName = txtAccountName.text
        settings.league = txtLeague.text
        settings.sessionId = txtSessionID.text
        settings.tabIndex = txtTabIndex.text.toInt()
    }

    fun saveSettings(actionEvent: ActionEvent) {
        getSettingFieldInfo()
        saveSettings()
        stage?.hide()
    }


}