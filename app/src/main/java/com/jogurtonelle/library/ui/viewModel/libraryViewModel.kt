package com.jogurtonelle.library.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState  = _uiState.asStateFlow()

    var _searchQuery: String by mutableStateOf("")
        private set

    fun onQueryChanged(query: String) {
        _searchQuery = query
    }

}