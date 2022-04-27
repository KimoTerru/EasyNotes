package it.kimoterru.easynotes.domain.action

import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.repository.NotesRepository

class DeleteNotes(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(easyNotes: Notes) {
        repository.deleteNotes(easyNotes)
    }
}