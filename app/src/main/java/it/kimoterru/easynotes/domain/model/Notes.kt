package it.kimoterru.easynotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import it.kimoterru.easynotes.ui.theme.*

@Entity
data class Notes(
    @PrimaryKey
    val id: Int? = null,
    val tittle: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val NoteColors = listOf(
            LightPink,
            LightYellow,
            LightBlue,
            LightGreen,
            DarkYellow,
            RedYellow
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)