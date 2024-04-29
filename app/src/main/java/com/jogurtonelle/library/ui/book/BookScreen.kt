package com.jogurtonelle.library.ui.book

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jogurtonelle.library.R
import com.jogurtonelle.library.model.BookCopy
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.model.User
import com.jogurtonelle.library.theme.Oswald

@SuppressLint("UnrememberedMutableState")
@Composable
fun BookScreen(
    bookTitle: BookTitle,
    getBookCopies: (String) -> List<BookCopy>,
    onBack: () -> Unit,
    onAddToFavourites: (String) -> Unit,
    user : User,
    modifier: Modifier = Modifier
){
    val availableCopies = getBookCopies(bookTitle.isbn)
    val libraryBranches = availableCopies.map { it.libraryBranchId }.distinct().sorted()
    var isFavourite by mutableStateOf(user.favourites.map(BookTitle::isbn).contains(bookTitle.isbn))

    Scaffold (
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = paddingValues,
        ){
            item{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    AsyncImage(
                        model = bookTitle.coverURL,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 16.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .weight(1f)
                    )

                    IconButton(onClick = {
                        onAddToFavourites(bookTitle.isbn)
                        isFavourite = !isFavourite },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = if (isFavourite) R.drawable.baseline_bookmark_24 else R.drawable.baseline_bookmark_border_24),
                            contentDescription = "Back"
                        )
                    }
                }
            }

            item {
                TitleAndDescription(book = bookTitle)
            }

            item{
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Zarezerwuj:",
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = Oswald,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 4.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(4.dp))
            }

            items(libraryBranches) { branch ->
                AvailableBookCopies(availableCopies = availableCopies, branch = branch)
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}