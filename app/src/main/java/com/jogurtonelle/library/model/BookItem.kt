package com.jogurtonelle.library.model

data class BookItem(
    val id: Int,
    val bookId: Int,
    val libraryBranchId: Int,
    val yearOfPublication: Int,
    val status: BookStatus,
    val dateOfReturn: String?
)