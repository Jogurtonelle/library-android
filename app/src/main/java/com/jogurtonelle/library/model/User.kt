package com.jogurtonelle.library.model

data class User(
    val uid: String = "1234",
    val cardID: String = "1234",
    val favourites: MutableList<BookTitle> = mutableListOf(),
    val notifications: MutableList<Notification> = mutableListOf(),
    val borrowedBooks: MutableList<BookTitle> = mutableListOf()
)