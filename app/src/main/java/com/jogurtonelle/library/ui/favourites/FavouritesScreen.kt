package com.jogurtonelle.library.ui.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.ui.searchBar.SearchResult

@Composable
fun FavouritesScreen(
    onCardClick: (BookTitle) -> Unit,
    favourites: List<BookTitle>,
    modifier : Modifier = Modifier,
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(favourites){
            favBookTitle ->
            SearchResult(book = favBookTitle, onCardClick = onCardClick)
        }
    }
}