package it.kimoterru.easynotes.domain.action

import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.repository.NotesRepository
import it.kimoterru.easynotes.domain.util.NotesOrder
import it.kimoterru.easynotes.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NotesRepository
) {
    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)
    ): Flow<List<Notes>> {
        return repository.getNotes().map { notes ->
            when (notesOrder.orderType) {
                is OrderType.Ascending -> {
                    when (notesOrder) {
                        is NotesOrder.Tittle -> notes.sortedBy { it.tittle.lowercase() }
                        is NotesOrder.Date -> notes.sortedBy { it.timestamp }
                        is NotesOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (notesOrder) {
                        is NotesOrder.Tittle -> notes.sortedByDescending { it.tittle.lowercase() }
                        is NotesOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NotesOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}