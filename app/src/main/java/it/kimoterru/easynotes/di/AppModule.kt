package it.kimoterru.easynotes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.kimoterru.easynotes.data.database.NotesDatabase
import it.kimoterru.easynotes.data.repository.NotesRepositoryImpl
import it.kimoterru.easynotes.domain.action.*
import it.kimoterru.easynotes.domain.repository.NotesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NotesDatabase): NotesRepository {
        return NotesRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNotes = DeleteNotes(repository),
            addNotes = AddNotes(repository),
            getNote = GetNote(repository)
        )
    }
}