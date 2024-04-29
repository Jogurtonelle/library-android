package com.jogurtonelle.library.ui.viewModel

import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.ui.LibraryScreen

data class LibraryUiState(
    var selectedBookTitleHomeScreen: BookTitle = BookTitle(
        isbn = "9788328710306",
        title = "Rodzina Monet. Diament. Część 1",
        description = "Byłam wolna. Długo poszukiwałam tego uczucia i teraz, gdy studiowałam w Barcelonie, moje życie poukładało się tak, że mogłam wreszcie się nim cieszyć. Moje myśli zaczęły krążyć wokół etapu, na którym się znajdowałam. Wszystko było tak, jak chciałam. Tak jak powinno. Niczego mi nie brakowało… Hailie Monet zasmakowała w życiu wszystkiego. Rozpaczy, strachu, miłości, bogactwa, wolności. Młoda studentka medycyny liczyła, że w końcu nastała mała stabilizacja. Nic bardziej mylnego. W dniu, w którym pod drzwiami jej barcelońskiego mieszkania pojawił się Adrien Santan, wszystko zaczyna się komplikować. Czwarty tom bestsellerowej serii \"Rodzina Monet\".",
        author = "Weronika Marczak",
        coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
        promotionRows = listOf(1, 2)
    ),
    var selectedBookTitleFavouritesScreen: BookTitle = selectedBookTitleHomeScreen,
    var showQrCodeBottomSheet: Boolean = false,
    var prevSearches: List<String> = listOf("Harry Potter", "The Witcher", "The Lord of the Rings"), //TODO: change to emptyList()
    var showSearchResults: Boolean = false,
    var searchResults: List<BookTitle> = listOf(),
    var userLoggedIn: Boolean = false,
    var currentScreen: LibraryScreen = LibraryScreen.HOME,
    var bookScreenMainActive: Boolean = false,
    var bookScreenFavActive: Boolean = false
)