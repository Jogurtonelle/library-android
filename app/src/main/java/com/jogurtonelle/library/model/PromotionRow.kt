package com.jogurtonelle.library.model

data class PromotionRow(
    val id: Int,
    val name: String,
    val books: List<BookTitle>
)