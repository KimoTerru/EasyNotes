package it.kimoterru.easynotes.presentation.notes

import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.util.NotesOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NotesOrder) : NotesEvent()
    data class DeleteNote(val note: Notes) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}