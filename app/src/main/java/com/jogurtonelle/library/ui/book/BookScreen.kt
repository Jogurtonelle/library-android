package com.jogurtonelle.library.ui.book

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.theme.Oswald

@Composable
fun BookScreen(
    bookTitleId : Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    val book = Data.bookTitles.first { it.id == bookTitleId }
    val availableCopies = Data.bookItems.filter { it.bookTitleId == book.id }.sortedBy { it.libraryBranchId }
    val libraryBranches = availableCopies.map { it.libraryBranchId }.distinct().sorted()

    Scaffold (
        containerColor = MaterialTheme.colorScheme.surface
    ){ paddingValues ->
        LazyColumn (
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = paddingValues,
        ){
            item{

                AsyncImage(
                    model = book.coverURL,
                    contentDescription = null,
                    modifier = modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
            }

            item {
                TitleAndDescription(book = book)
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