package com.jogurtonelle.library.ui.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.model.Book

@Composable
fun TitleDescription(
    book: Book
){
    var linesToShow by remember { mutableStateOf(4) }
    var descriptionCallToAction by remember { mutableStateOf("rozwiń opis") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .clickable(
                onClick = {
                    linesToShow = if (linesToShow == 4) Int.MAX_VALUE else 4
                    descriptionCallToAction =
                        if (linesToShow == 4) "rozwiń opis" else "zwiń opis"
                }
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = false,
                    onClick = {}
                ),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = linesToShow
        )

        Text(
            text = descriptionCallToAction,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(end = 16.dp, bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}