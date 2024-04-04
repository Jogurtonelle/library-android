package com.jogurtonelle.library.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.jogurtonelle.library.R
import com.jogurtonelle.library.ui.LibraryScreen

@Composable
fun BottomNavBar(
    onClick: (LibraryScreen) -> Unit,
    currentSelection: LibraryScreen,
    modifier: Modifier = Modifier
){
    val navigationBarItemColors = NavigationBarItemColors(
        selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        selectedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
    )

    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = (currentSelection == LibraryScreen.HOME || currentSelection == LibraryScreen.BOOK_HOME),
            onClick =  { onClick(LibraryScreen.HOME) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Strona główna"
                )
            },
            colors = navigationBarItemColors,
            label = { Text(text = "Strona główna", maxLines = 1, overflow = TextOverflow.Ellipsis) }
        )

        NavigationBarItem(
            selected = (currentSelection == LibraryScreen.FAV),
            onClick =  {onClick(LibraryScreen.FAV)},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bookmarks_24),
                    contentDescription = "Zapisane"
                )
            },
            colors = navigationBarItemColors,
            label = { Text(text = "Zapisane", maxLines = 1, overflow = TextOverflow.Ellipsis) }
        )

        NavigationBarItem(
            selected = (currentSelection == LibraryScreen.NOTIFICATIONS),
            onClick =  {onClick(LibraryScreen.NOTIFICATIONS)},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    contentDescription = "Powiadomienia"
                )
            },
            colors = navigationBarItemColors,
            label = { Text(text = "Powiadomienia", maxLines = 1, overflow = TextOverflow.Ellipsis) }
        )

        NavigationBarItem(
            selected = (currentSelection == LibraryScreen.PROFILE),
            onClick =  {onClick(LibraryScreen.PROFILE)},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_emoji_emotions_24),
                    contentDescription = "Profil"
                )
            },
            colors = navigationBarItemColors,
            label = { Text(text = "Profil", maxLines = 1, overflow = TextOverflow.Ellipsis) }
        )
    }
}