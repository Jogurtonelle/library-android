package com.jogurtonelle.library.model

data class BookCopy(
    val id: Int,
    val bookTitleId: Int,
    val libraryBranchId: Int,
    val yearOfPublication: Int,
    val status: BookStatus,
    val dateOfReturn: String?
)