package it.kimoterru.easynotes.presentation.addediteasynote

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)