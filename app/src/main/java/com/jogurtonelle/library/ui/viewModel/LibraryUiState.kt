package com.jogurtonelle.library.ui.viewModel

import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.ui.LibraryScreen

data class LibraryUiState(
    var selectedBookTitleIdHomeScreen: Int = 1,
    var selectedBookTitleIdFavouritesScreen: Int = 1,
    var showQrCodeBottomSheet: Boolean = false,
    var prevSearches: List<String> = listOf("Harry Potter", "The Witcher", "The Lord of the Rings"), //TODO: change to emptyList()
    var showSearchResults: Boolean = false,
    var searchResults: List<BookTitle> = listOf(),
    var currentScreen: LibraryScreen = LibraryScreen.HOME,
    var bookScreenMainActive: Boolean = false,
    var bookScreenFavActive: Boolean = false
)