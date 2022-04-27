package it.kimoterru.easynotes.domain.action

import it.kimoterru.easynotes.domain.model.InvalidNoteException
import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.repository.NotesRepository

class AddNotes(
    private val repository: NotesRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(easyNotes: Notes) {
        if (easyNotes.tittle.isEmpty()) {
            throw InvalidNoteException("Please enter Title!")
        }
        if (easyNotes.content.isEmpty()) {
            throw InvalidNoteException("Please enter Content!")
        }
        repository.insertNotes(easyNotes)
    }
}