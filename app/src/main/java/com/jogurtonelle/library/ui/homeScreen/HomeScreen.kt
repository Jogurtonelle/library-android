package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.R
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.ui.barcodeScreen.CodeDrawer
import com.jogurtonelle.library.ui.barcodeScreen.FloatingActionButtonCard
import com.jogurtonelle.library.ui.topBar.TopBarCollapsed
import com.jogurtonelle.library.ui.viewModel.LibraryUiState

@Composable
fun HomeScreen(
    libraryUiState: LibraryUiState,
    onLibraryTextFieldValueChange: (String) -> Unit,
    libraryTextFieldValue: String,
    searchBarFocused: Boolean,
    onBookClicked: (Int) -> Unit,
    onBarcodeDismissRequest: () -> Unit,
    onYourCardClick: () -> Unit,
    onSearchBarFocusChange: (Boolean) -> Unit,
    prevSearches: List<String>
) {
    Scaffold(
        floatingActionButton = { FloatingActionButtonCard(onYourCardClick = onYourCardClick) },
        topBar = {TopBarCollapsed(
            query = libraryTextFieldValue,
            onQueryChange = onLibraryTextFieldValueChange,
            onSearch = {}, //TODO
            isActive = searchBarFocused,
            onActiveChange = onSearchBarFocusChange,
            onOpenMenu = {}, //TODO
            prevSearches = prevSearches
        )},
        modifier = Modifier.fillMaxSize(),
    ) {
        innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                item {
                    Text(
                        text = "Witaj, Mariusz!",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )


                }
                item{
                    //LibraryTextField(onValueChange = onLibraryTextFieldValueChange, value = libraryTextFieldValue)
                }

                item{
                    BookCarousel(books = Data.books, title = "Nowości", onBookClick = onBookClicked)
                }
                item{
                    BookCarousel(books = Data.books, title = "Bestsellery", onBookClick = onBookClicked)

                }
                item{
                    BookCarousel(books = Data.books, title = "Polecane", onBookClick = onBookClicked)
                }
            }
        }

    }

    if (libraryUiState.showBarcodeBottomSheet) {
        CodeDrawer(
            onDismissRequest = onBarcodeDismissRequest
        )
    }
}