package com.jogurtonelle.library.ui.viewModel

data class LibraryUiState(
    var selectedBookTitleId: Int? = null,
    var showQrCodeBottomSheet: Boolean = false,
    var prevSearches: List<String> = listOf("Harry Potter", "The Witcher", "The Lord of the Rings") //TODO: change to emptyList()
)