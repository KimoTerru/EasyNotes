package it.kimoterru.easynotes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.easynotes.domain.action.NoteUseCases
import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.util.NotesOrder
import it.kimoterru.easynotes.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val stateEasy: State<NotesState> = _state

    private var recentlyDeletedNote: Notes? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(eventEasy: NotesEvent) {
        when (eventEasy) {
            is NotesEvent.Order -> {
                if (stateEasy.value.noteOrder::class == eventEasy.noteOrder::class &&
                    stateEasy.value.noteOrder.orderType == eventEasy.noteOrder.orderType
                ) {
                    return
                }
                getNotes(eventEasy.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNotes(eventEasy.note)
                    recentlyDeletedNote = eventEasy.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNotes(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = stateEasy.value.copy(
                    isOrderSectionVisible = !stateEasy.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NotesOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = stateEasy.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}