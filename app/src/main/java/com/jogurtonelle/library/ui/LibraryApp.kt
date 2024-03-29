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
) {
    val libraryUiState by libraryViewModel.uiState.collectAsState()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onLibraryTextFieldValueChange = { libraryViewModel.onQueryChanged(it) },
                libraryTextFieldValue = libraryViewModel._searchQuery,
                onBookClicked = {
                    libraryUiState.selectedBookId = it
                    libraryViewModel.onQueryChanged("")
                    navController.navigate("book")
                }
            )
        }
        composable("book") {
            BookScreen(
                bookId = libraryUiState.selectedBookId ?: 0,
                onBack = { navController.popBackStack() }
            )
        }
    }
}