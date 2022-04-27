package it.kimoterru.easynotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.kimoterru.easynotes.domain.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "myNotes_db"
    }
}