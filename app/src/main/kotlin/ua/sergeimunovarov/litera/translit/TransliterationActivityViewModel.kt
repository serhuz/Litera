/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import ua.sergeimunovarov.litera.DispatcherRegistry
import ua.sergeimunovarov.litera.VoidLiveEvent
import ua.sergeimunovarov.litera.db.Item
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.translit.translator.TranslatorTaskFactory

@FlowPreview
@ExperimentalCoroutinesApi
class TransliterationActivityViewModel(private val state: SavedStateHandle,
                                       private val prefs: Prefs,
                                       itemDAO: ItemDAO) : ViewModel() {

    val itemsLiveData = itemDAO.getLatest(3, prefs.setting.standard)
    val input: MutableLiveData<String>
    val romanized: LiveData<String>
    val itemsLoaded = MutableLiveData(false)
    val exitEvent = VoidLiveEvent()

    var items = emptyList<Item>()

    private val translator by lazy { TranslatorTaskFactory.createTranslator(prefs.setting.standard) }

    init {
        input = state.getLiveData(KEY_INPUT, "")
                .also { input ->
                    romanized = input.asFlow()
                            .onEach { state[KEY_INPUT] = it }
                            .flatMapConcat { romanizeText(it.trim()) }
                            .asLiveData(DispatcherRegistry.main + viewModelScope.coroutineContext)
                }
    }

    private fun romanizeText(text: String) =
            flow { emit(translator.replaceCharacters(text)) }
                    .flowOn(DispatcherRegistry.io)


    fun exit() {
        exitEvent.call()
    }

    fun clearText() {
        input.value = ("")
    }

    fun hasText() = input.value.orEmpty().isNotBlank() && romanized.value.orEmpty().isNotBlank()

    fun updateItems(items: List<Item>) {
        this.items = items
        itemsLoaded.value = true
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(owner: SavedStateRegistryOwner,
                  private val prefs: Prefs,
                  private val itemDAO: ItemDAO) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T =
                TransliterationActivityViewModel(handle, prefs, itemDAO) as T
    }

    companion object {
        const val KEY_INPUT = "input"
    }
}
