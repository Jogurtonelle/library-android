package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.model.Book
import com.jogurtonelle.library.theme.Oswald

@Composable
fun BookCarousel(
    books: List<Book>,
    title: String,
    onBookClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp, bottom = 4.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = Oswald
        )

        LazyRow (
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(books) { book ->
                BookCard(
                    book = book,
                    onCardClicked = { onBookClick(book.id) }
                )
            }
        }
    }
}

