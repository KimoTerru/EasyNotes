package it.kimoterru.easynotes.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}