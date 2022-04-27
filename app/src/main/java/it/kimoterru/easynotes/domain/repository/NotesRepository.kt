package it.kimoterru.easynotes.domain.repository

import it.kimoterru.easynotes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes(): Flow<List<Notes>>

    suspend fun getNotesById(id: Int): Notes?

    suspend fun insertNotes(note: Notes)

    suspend fun deleteNotes(note: Notes)
}