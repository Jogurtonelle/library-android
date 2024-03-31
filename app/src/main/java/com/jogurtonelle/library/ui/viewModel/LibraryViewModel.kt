package com.jogurtonelle.library.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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

    fun onQueryChanged(query: String) {
        _searchQuery = query
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