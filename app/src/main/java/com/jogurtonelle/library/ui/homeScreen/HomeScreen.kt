package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.model.PromotionRow
import com.jogurtonelle.library.ui.qrCodeSheet.QrCodeBottomSheet
import com.jogurtonelle.library.ui.qrCodeSheet.QrCodeFloatingActionButton
import com.jogurtonelle.library.ui.searchBar.LibrarySearchBar
import com.jogurtonelle.library.ui.viewModel.LibraryUiState

@Composable
fun HomeScreen(
    libraryUiState: LibraryUiState,
    cardID: String,
    onLibrarySearchBarValueChange: (String) -> Unit,
    librarySearchBarValue: String,
    searchBarFocused: Boolean,
    onBookClicked: (BookTitle) -> Unit,
    onBarcodeDismissRequest: () -> Unit,
    onYourCardClick: () -> Unit,
    onSearchBarFocusChange: (Boolean) -> Unit,
    prevSearches: List<String>,
    promotionRows: List<PromotionRow>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = { QrCodeFloatingActionButton(onButtonClick = onYourCardClick) },
        topBar = {
            LibrarySearchBar(
                query = librarySearchBarValue,
                onQueryChange = onLibrarySearchBarValueChange,
                onSearch = {}, //TODO
                isActive = searchBarFocused,
                onActiveChange = onSearchBarFocusChange,
                prevSearches = prevSearches,
                searchResults = libraryUiState.searchResults,
                ifShowResults = libraryUiState.showSearchResults,
                onBookClick = onBookClicked
            )
        },
        modifier = modifier
            .fillMaxSize(),
    ) {
        innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                item {
                    Text(
                        text = "Witaj, Mariusz!",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                for (promotionRow in promotionRows) {
                    item {
                        BookCarousel(books = promotionRow.books, title = promotionRow.name, onBookClick = onBookClicked)
                    }
                }

//                item{
//                    BookCarousel(books = Data.bookTitles, title = "Nowości", onBookClick = onBookClicked)
//                }
//                item{
//                    BookCarousel(books = Data.bookTitles, title = "Bestsellery", onBookClick = onBookClicked)
//
//                }
//                item{
//                    BookCarousel(books = Data.bookTitles, title = "Polecane", onBookClick = onBookClicked)
//                }
            }
        }

    }

    if (libraryUiState.showQrCodeBottomSheet) {
        QrCodeBottomSheet(
            cardID = cardID,
            onDismissRequest = onBarcodeDismissRequest
        )
    }
}