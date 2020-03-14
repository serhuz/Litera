/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import ua.sergeimunovarov.litera.DispatcherRegistry
import ua.sergeimunovarov.litera.StringProvider
import ua.sergeimunovarov.litera.VoidLiveEvent
import ua.sergeimunovarov.litera.db.Item
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.db.Standard
import ua.sergeimunovarov.litera.db.getStandardsForLanguage
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.prefs.Setting

@ExperimentalCoroutinesApi
class MainActivityViewModel(private val itemDAO: ItemDAO,
                            private val prefs: Prefs,
                            private val stringProvider: StringProvider) : ViewModel() {

    val historyItems = itemDAO.itemsByTimestamp().toLiveData(pageSize = 25)

    val standardSettingsClickEvent = VoidLiveEvent()
    val langSettingsClickEvent = VoidLiveEvent()
    val inputClickEvent = VoidLiveEvent()
    val cameraRecognitionEvent = VoidLiveEvent()
    val voiceRecognitionEvent = VoidLiveEvent()

    val langPref = MutableLiveData("")
    val standardPref = MutableLiveData("")
    val itemsLoaded = MutableLiveData(false)

    init {
        getCurrentStandard().let {
            langPref.value = it.lang.name
            standardPref.value = stringProvider.getString(it.displayName)
        }
    }

    fun openLangSettings() {
        langSettingsClickEvent.call()
    }

    fun openSettings() {
        standardSettingsClickEvent.call()
    }

    fun openInput() {
        inputClickEvent.call()
    }

    fun startCameraRecognition() {
        cameraRecognitionEvent.call()
    }

    fun startVoiceRecognition() {
        voiceRecognitionEvent.call()
    }

    fun getCurrentLang() = prefs.setting.standard.lang

    fun getStandardsForCurrentLang() = getStandardsForLanguage(prefs.setting.standard.lang)

    fun setStandard(standard: Standard) {
        standardPref.value = stringProvider.getString(standard.displayName)
        prefs.setting = Setting(standard)
    }

    fun setFirstAvailableStandardForLang(name: String) {
        langPref.value = name
        Standard.values()
                .find { it.lang.name == name }
                ?.let(this::setStandard)
                ?: error("Standard not found")
    }

    fun saveResult(input: String, romanized: String) {
        flow { emit(itemDAO.insert(Item(original = input, romanized = romanized, standard = prefs.setting.standard))) }
                .flowOn(DispatcherRegistry.io)
                .launchIn(viewModelScope)
    }

    fun removeItem(item: Item) {
        flow { emit(item.id?.let(itemDAO::deleteItemById)) }
                .flowOn(DispatcherRegistry.io)
                .launchIn(viewModelScope)
    }

    private fun getCurrentStandard() = prefs.setting.standard
}
