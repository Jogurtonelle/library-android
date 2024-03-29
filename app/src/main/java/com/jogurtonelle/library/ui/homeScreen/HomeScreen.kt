package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.data.Data

@Composable
fun HomeScreen(
    onLibraryTextFieldValueChange: (String) -> Unit,
    libraryTextFieldValue: String,
    onBookClicked: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box{
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
                    LibraryTextField(onValueChange = onLibraryTextFieldValueChange, value = libraryTextFieldValue)
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
}