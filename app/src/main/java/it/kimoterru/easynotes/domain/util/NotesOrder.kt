package it.kimoterru.easynotes.domain.util

sealed class NotesOrder(val orderType: OrderType) {
    class Tittle(orderType: OrderType) : NotesOrder(orderType)
    class Date(orderType: OrderType) : NotesOrder(orderType)
    class Color(orderType: OrderType) : NotesOrder(orderType)

    fun copy(orderType: OrderType): NotesOrder {
        return when (this) {
            is Tittle -> Tittle(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}