/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import ua.sergeimunovarov.litera.VoidLiveEvent
import ua.sergeimunovarov.litera.db.Item
import ua.sergeimunovarov.litera.translit.translator.TranslatorFactory

@FlowPreview
@ExperimentalCoroutinesApi
class TransliterationActivityViewModel(private val state: SavedStateHandle,
                                       private val factory: TranslatorFactory) : ViewModel() {

    val input: MutableLiveData<String>
    val romanized: LiveData<String>
    val exitEvent = VoidLiveEvent()

    var items = emptyList<Item>()

    private val translator by lazy { factory.createTranslator() }

    init {
        input = state.getLiveData(KEY_INPUT, "")
                .also { input ->
                    romanized = input.asFlow()
                            .onEach { state[KEY_INPUT] = it }
                            .flatMapConcat { romanizeText(it.trim()) }
                            .asLiveData(Dispatchers.Main + viewModelScope.coroutineContext)
                }
    }

    private fun romanizeText(text: String) =
            flow { emit(translator.replaceCharacters(text)) }
                    .flowOn(Dispatchers.IO)

    fun exit() {
        exitEvent.call()
    }

    fun clearText() {
        input.value = ("")
    }

    fun hasText() = input.value.orEmpty().isNotBlank() && romanized.value.orEmpty().isNotBlank()

    companion object {
        const val KEY_INPUT = "input"
    }
}
