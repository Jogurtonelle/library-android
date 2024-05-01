package com.jogurtonelle.library.model

data class BookCopy(
    val id: Int,
    val bookTitleISBN: String,
    val libraryBranchId: Int,
    val yearOfPublication: Int,
    var status: BookStatus,
    var dateOfReturn: String?
)