package com.jogurtonelle.library.model

data class BookTitle(
    val isbn: String = "",
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val coverURL: String = "",
    val promotionRows: List<Int> = emptyList(),
)