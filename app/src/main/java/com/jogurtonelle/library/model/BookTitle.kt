package com.jogurtonelle.library.model

data class BookTitle(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val ISBN: String,
    val coverURL: String
)