package it.kimoterru.easynotes.domain.action

import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.repository.NotesRepository

class GetNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int): Notes? {
        return repository.getNotesById(id)
    }
}