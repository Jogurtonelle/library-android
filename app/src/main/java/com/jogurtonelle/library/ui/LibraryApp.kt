package com.jogurtonelle.library.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jogurtonelle.library.ui.book.BookScreen
import com.jogurtonelle.library.ui.homeScreen.HomeScreen
import com.jogurtonelle.library.ui.viewModel.LibraryViewModel

@Composable
fun LibraryApp(
    libraryViewModel: LibraryViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val libraryUiState by libraryViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = LibraryScreen.HOME.name
    ) {
        composable(LibraryScreen.HOME.name) {
            HomeScreen(
                libraryUiState = libraryUiState,
                onLibrarySearchBarValueChange = { libraryViewModel.onQueryChanged(it) },
                librarySearchBarValue = libraryViewModel._searchQuery,
                onBookClicked = {
                    libraryViewModel.selectBook(it)
                    libraryViewModel.onQueryChanged("")
                    navController.navigate(LibraryScreen.BOOK.name)
                },
                onBarcodeDismissRequest = { libraryViewModel.changeBarcodeSheetVisibility(false) },
                onYourCardClick = { libraryViewModel.changeBarcodeSheetVisibility(true) },
                onSearchBarFocusChange = { libraryViewModel.onSearchBarFocusChange(it) },
                searchBarFocused = libraryViewModel._searchBarFocused,
                prevSearches = libraryUiState.prevSearches
            )
        }

        composable(LibraryScreen.BOOK.name) {
            BookScreen(
                bookTitleId = libraryUiState.selectedBookTitleId ?: 0,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

enum class LibraryScreen {
    HOME, BOOK
}