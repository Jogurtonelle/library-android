package com.jogurtonelle.library.ui.searchBar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.R
import com.jogurtonelle.library.model.BookTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrarySearchBar(
    query : String,
    ifShowResults: Boolean,
    onQueryChange : (String) -> Unit,
    onSearch : (String) -> Unit,
    isActive: Boolean,
    onActiveChange : (Boolean) -> Unit,
    prevSearches: List<String>,
    searchResults: List<BookTitle>,
    onBookClick: (Int) -> Unit,
    onOpenMenu: () -> Unit ={},
    modifier: Modifier = Modifier
) {
    val horizontalPadding by animateDpAsState(targetValue = if (isActive) 0.dp else 16.dp,
        label = "searchBarHorizontalPadding"
    )

    val verticalPadding by animateDpAsState(targetValue = if (isActive) 0.dp else 8.dp,
        label = "searchBarVerticalPadding"
    )

    SearchBar(
        query = query,
        onQueryChange = {
            onQueryChange(it)
        },
        onSearch = onSearch,
        active = isActive,
        onActiveChange = {onActiveChange(it)},
        placeholder = { Text(text = "Wyszukaj tytuł, autora lub numer ISBN") },
        leadingIcon = {
            IconButton(
                onClick = { if (isActive) onActiveChange(false) else onOpenMenu() },
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp)
            ) {
                Icon(
                    imageVector = if (isActive) Icons.AutoMirrored.Filled.ArrowBack else Icons.Filled.Menu,
                    contentDescription = if (isActive) "Back" else "Menu",
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        tonalElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = horizontalPadding, end = horizontalPadding, top = verticalPadding)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            if (ifShowResults){
                if (searchResults.isEmpty()){
                    item{
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_off_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp, top = 24.dp)
                                    .size(160.dp)
                            )
                            Text(text = "Brak wyników wyszukiwania")
                        }
                    }
                }
                else{
                    items(searchResults){
                        SearchResult(
                            book = it,
                            onCardClick = onBookClick
                        )
                    }
                }
            }
            else {
                items(prevSearches) {
                    Box() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .clickable(
                                    onClick = {
                                        onQueryChange(it)
                                        onSearch(it)
                                    }
                                )
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_history_24),
                                contentDescription = null,
                                modifier = Modifier
                                    //.size(16.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}

