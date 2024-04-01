package com.jogurtonelle.library.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.model.BookTitle
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
            if (_prevSearchQuerry.isNotEmpty() && _prevSearchQuerry.length < 15 && _prevSearchQuerry.length < query.length && query.contains(_prevSearchQuerry)){
                searchResults = _prevSearchResults.filter { it.title.contains(query, ignoreCase = true) }.take(15)
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

                //Searching for ISBN
                if (searchResults.isEmpty() && query.length > 2) {
                    searchResults = searchResults + Data.bookTitles.filter {
                        it.ISBN.startsWith(
                            query,
                            ignoreCase = true
                        )
                    }.take(15)
                }
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
    fun selectBook(bookId: Int) {
        _uiState.value.selectedBookTitleId = bookId
    }

    fun resetSelectedBook() {
        _uiState.value.selectedBookTitleId = null
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
}