package com.jogurtonelle.library.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.ui.LibraryScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState  = _uiState.asStateFlow()

    /** The current query for the search bar */
    var _searchQuery: String by mutableStateOf("")
        private set
    //TODO??
    var _searchBarFocused : Boolean by mutableStateOf(false)
        private set

    private var _prevSearchResults: List<BookTitle> = listOf()
    private var _prevSearchQuerry: String = ""

    fun onQueryChanged(query: String){
        _searchQuery = query

        var searchResults: List<BookTitle>

        if (query.isNotEmpty()){
            //New search results contain the previous search results
            if (_prevSearchQuerry.isNotEmpty() && _prevSearchResults.size < 15 && query.contains(_prevSearchQuerry)){
                searchResults = _prevSearchResults.filter { it.title.contains(query, ignoreCase = true) }.take(15)

                //Searching for author
                if (searchResults.isEmpty() || searchResults.size < 15){
                    searchResults = searchResults + _prevSearchResults.filter { it.author.contains(query, ignoreCase = true) && !searchResults.contains(it)}.take(15 - searchResults.size)
                }
            }
            else{
                searchResults = Data.bookTitles.filter { it.title.startsWith(query, ignoreCase = true) }.take(15)
                if (searchResults.isEmpty() || searchResults.size < 15){
                    searchResults = searchResults + Data.bookTitles.filter { it.title.contains(query, ignoreCase = true) && !searchResults.contains(it)}.take(15 - searchResults.size)
                }
                //Searching for author
                if (searchResults.isEmpty() || searchResults.size < 15){
                    searchResults = searchResults + Data.bookTitles.filter { it.author.contains(query, ignoreCase = true) && !searchResults.contains(it)}.take(15 - searchResults.size)
                }
            }

            if (searchResults.isEmpty() && query.all{ it.isDigit() }){
                searchResults = Data.bookTitles.filter { it.ISBN.startsWith(query) }
            }

            _uiState.update {
                it.copy(showSearchResults = true, searchResults = searchResults)
            }

            _prevSearchResults = searchResults
            _prevSearchQuerry = query
        }
        else{
            _uiState.update {
                it.copy(showSearchResults = false, searchResults = listOf())
            }

            _prevSearchResults = listOf()
            _prevSearchQuerry = ""
        }
    }

    //Book selection
    fun selectBookHomeScreen(bookId: Int) {
        _uiState.value.selectedBookTitleIdHomeScreen = bookId
    }

    fun selectBookFavouritesScreen(bookId: Int) {
        _uiState.value.selectedBookTitleIdFavouritesScreen = bookId
    }

    fun changeBarcodeSheetVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(showQrCodeBottomSheet = isVisible)}
    }

    fun onSearchBarFocusChange(isFocused: Boolean) {
        _searchBarFocused = isFocused
        if (!isFocused){
            _searchQuery = ""
        }
    }

    fun addPrevSearch(search: String) {
        _uiState.update {
            it.copy(prevSearches = it.prevSearches + search)
        }
    }

    fun setCurrentScreen(screen: LibraryScreen){
        if (screen == LibraryScreen.BOOK_HOME) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenMainActive = true
                )
            }
            return
        }

        if (screen == LibraryScreen.BOOK_FAV) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenFavActive = true
                )
            }
            return
        }

        if (screen == LibraryScreen.HOME && _uiState.value.bookScreenMainActive) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenMainActive = false
                )
            }
            onSearchBarFocusChange(false)
            onQueryChanged("")
            return
        }

        if (screen == LibraryScreen.FAV && _uiState.value.bookScreenFavActive) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenFavActive = false
                )
            }
            return
        }

        _uiState.update {
            it.copy(
                currentScreen = screen
            )
        }
    }

    fun editFavourites(bookId: Int){
        val book = Data.bookTitles.find { it.id == bookId }
        if (book != null && !Data.favourites.contains(book)){
            Data.favourites.add(book)
            return
        }
        if (book != null && Data.favourites.contains(book)){
            Data.favourites.remove(book)
        }
    }
}