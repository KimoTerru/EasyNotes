package it.kimoterru.easynotes.data.repository

import it.kimoterru.easynotes.data.database.NoteDao
import it.kimoterru.easynotes.domain.model.Notes
import it.kimoterru.easynotes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val dao: NoteDao
) : NotesRepository {

    override fun getNotes(): Flow<List<Notes>> {
        return dao.getNotes()
    }

    override suspend fun getNotesById(id: Int): Notes? {
        return dao.getNotesById(id)
    }

    override suspend fun insertNotes(note: Notes) {
        dao.insertNotes(note)
    }

    override suspend fun deleteNotes(note: Notes) {
        dao.deleteNotes(note)
    }
}