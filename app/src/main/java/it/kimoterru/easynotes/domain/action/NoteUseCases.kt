package it.kimoterru.easynotes.domain.action

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNotes: AddNotes,
    val getNote: GetNote
)