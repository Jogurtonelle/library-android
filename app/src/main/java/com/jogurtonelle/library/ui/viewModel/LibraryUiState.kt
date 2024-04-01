package com.jogurtonelle.library.ui.viewModel

import com.jogurtonelle.library.model.BookTitle

data class LibraryUiState(
    var selectedBookTitleId: Int? = null,
    var showQrCodeBottomSheet: Boolean = false,
    var prevSearches: List<String> = listOf("Harry Potter", "The Witcher", "The Lord of the Rings"), //TODO: change to emptyList()
    var showSearchResults: Boolean = false,
    var searchResults: List<BookTitle> = listOf()
)