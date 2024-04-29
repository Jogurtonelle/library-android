package com.jogurtonelle.library.ui.searchBar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchResults(
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            LibrarySearchBar(
                query = "",
                onQueryChange = {},
                onSearch = {},
                isActive = true,
                onActiveChange = {},
                onOpenMenu = {},
                prevSearches = listOf(),
                searchResults = listOf(),
                ifShowResults = true,
                onBookClick = {  }
            )
        }

    ){
        paddingValues ->
        LazyColumn (
            modifier = modifier.padding(paddingValues)
        ){

        }
    }
}