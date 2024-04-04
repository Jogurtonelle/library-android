package com.jogurtonelle.library.ui.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.ui.searchBar.SearchResult

@Composable
fun FavouritesScreen(
    modifier : Modifier = Modifier,
    onCardClick: (Int) -> Unit
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(Data.favourites){
            favBookTitle ->
            SearchResult(book = favBookTitle, onCardClick = onCardClick)
        }
    }
}