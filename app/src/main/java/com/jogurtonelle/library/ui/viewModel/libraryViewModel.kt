package com.jogurtonelle.library.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState  = _uiState.asStateFlow()

    var _searchQuery: String by mutableStateOf("")
        private set
    var _searchBarFocused : Boolean by mutableStateOf(false)
        private set

    fun onQueryChanged(query: String) {
        _searchQuery = query
    }

    fun selectBook(bookId: Int) {
        _uiState.value.selectedBookId = bookId
    }

    fun resetSelectedBook() {
        _uiState.value.selectedBookId = null
    }

    fun showBarcodeSheet() {
        _uiState.update {
            it.copy(showBarcodeBottomSheet = true)}
    }

    fun hideBarcodeSheet() {
        _uiState.update { uiState ->
            uiState.copy(showBarcodeBottomSheet = false)}
    }

    fun onSearchBarFocusChange(focused: Boolean) {
        _searchBarFocused = focused
        if (!focused){
            _searchQuery = ""
        }
    }

    fun addPrevSearch(search: String) {
        _uiState.update {
            it.copy(prevSearches = it.prevSearches + search)
        }
    }
}