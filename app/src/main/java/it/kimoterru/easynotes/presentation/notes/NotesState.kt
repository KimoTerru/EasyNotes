package it.kimoterru.easynotes.presentation.notes

import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.util.NotesOrder
import it.kimoterru.easynotes.domain.util.OrderType

data class NotesState(
    val notes: List<Notes> = emptyList(),
    val noteOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)