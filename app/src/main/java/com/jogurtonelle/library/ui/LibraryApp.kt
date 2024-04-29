package com.jogurtonelle.library.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jogurtonelle.library.ui.book.BookScreen
import com.jogurtonelle.library.ui.common.BottomNavBar
import com.jogurtonelle.library.ui.favourites.FavouritesScreen
import com.jogurtonelle.library.ui.homeScreen.HomeScreen
import com.jogurtonelle.library.ui.login.LoginScreen
import com.jogurtonelle.library.ui.notifications.NotificationsScreen
import com.jogurtonelle.library.ui.viewModel.LibraryViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun LibraryApp(
    libraryViewModel: LibraryViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
){
    val libraryUiState by libraryViewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                onClick = {
                    //onHomeClick
                    if (it == LibraryScreen.HOME){
                        if (libraryUiState.bookScreenMainActive && libraryUiState.currentScreen != LibraryScreen.BOOK_HOME){
                            libraryViewModel.setCurrentScreen(LibraryScreen.BOOK_HOME)
                            navController.navigate(LibraryScreen.BOOK_HOME.name)
                        }
                        else{
                            libraryViewModel.setCurrentScreen(LibraryScreen.HOME)
                            navController.navigate(LibraryScreen.HOME.name)
                        }
                        return@BottomNavBar
                    }

                    if (it == LibraryScreen.FAV){
                        if (libraryUiState.bookScreenFavActive && libraryUiState.currentScreen != LibraryScreen.BOOK_FAV){
                            libraryViewModel.setCurrentScreen(LibraryScreen.BOOK_FAV)
                            navController.navigate(LibraryScreen.BOOK_FAV.name)
                        }
                        else{
                            libraryViewModel.setCurrentScreen(LibraryScreen.FAV)
                            navController.navigate(LibraryScreen.FAV.name)
                        }
                        return@BottomNavBar
                    }

                    libraryViewModel.setCurrentScreen(it)
                    navController.navigate(it.name)
                },
                currentSelection = libraryUiState.currentScreen
            )
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = libraryUiState.currentScreen.name
        ) {
            composable(LibraryScreen.HOME.name) {
                HomeScreen(
                    libraryUiState = libraryUiState,
                    onLibrarySearchBarValueChange = { libraryViewModel.onQueryChanged(it) },
                    librarySearchBarValue = libraryViewModel._searchQuery,
                    onBookClicked = {
                        libraryViewModel.selectBookHomeScreen(it)
                        libraryViewModel.setCurrentScreen(LibraryScreen.BOOK_HOME)
                        navController.navigate(LibraryScreen.BOOK_HOME.name)
                    },
                    onBarcodeDismissRequest = { libraryViewModel.changeBarcodeSheetVisibility(false) },
                    onYourCardClick = { libraryViewModel.changeBarcodeSheetVisibility(true) },
                    onSearchBarFocusChange = { libraryViewModel.onSearchBarFocusChange(it) },
                    searchBarFocused = libraryViewModel._searchBarFocused,
                    prevSearches = libraryUiState.prevSearches,
                    promotionRows = libraryViewModel._promotionRows,
                    cardID = libraryViewModel.user.cardID,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(LibraryScreen.BOOK_HOME.name) {
                BookScreen(
                    bookTitle = libraryUiState.selectedBookTitleHomeScreen,
                    getBookCopies = { runBlocking { libraryViewModel.getBookCopies(it) } },
                    onBack = { navController.popBackStack() },
                    onAddToFavourites = { libraryViewModel.editFavourites(it) },
                    user = libraryViewModel.user,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            composable(LibraryScreen.FAV.name) {
                FavouritesScreen(
                    modifier = Modifier.padding(paddingValues),
                    favourites = libraryViewModel.user.favourites,
                    onCardClick = {
                        libraryViewModel.selectBookFavouritesScreen(it)
                        libraryViewModel.setCurrentScreen(LibraryScreen.BOOK_FAV)
                        navController.navigate(LibraryScreen.BOOK_FAV.name)
                    }
                )
            }

            composable(LibraryScreen.BOOK_FAV.name) {
                BookScreen(
                    bookTitle = libraryUiState.selectedBookTitleFavouritesScreen,
                    getBookCopies = { runBlocking { libraryViewModel.getBookCopies(it) } },
                    onBack = { navController.popBackStack() },
                    user = libraryViewModel.user,
                    onAddToFavourites = { libraryViewModel.editFavourites(it) },
                    modifier = Modifier.padding(paddingValues),
                )
            }

            composable(LibraryScreen.NOTIFICATIONS.name) {
                NotificationsScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(LibraryScreen.PROFILE.name) {
                Button(onClick = { libraryViewModel.logout() }) {
                     Text("Logout")
                }
            }

            composable(LibraryScreen.LOGIN.name) {
                LoginScreen(onLoginClick = { email, password ->
                    libraryViewModel.login(email, password)
                })
            }
        }
    }
}

enum class LibraryScreen{
    HOME, BOOK_HOME, FAV, BOOK_FAV, NOTIFICATIONS, PROFILE, LOGIN
}