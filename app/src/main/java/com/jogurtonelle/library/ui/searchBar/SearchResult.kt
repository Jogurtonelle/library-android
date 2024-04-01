package com.jogurtonelle.library.ui.searchBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.model.BookTitle

@Composable
fun SearchResult(
    book: BookTitle,
    onCardClick : (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onCardClick(book.id) }
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ){
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            AsyncImage(
                model = book.coverURL,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(80.dp)
                    .aspectRatio(0.67f)
            )
            Column(

            ){
                Text(
                    text = book.title,
                    modifier = Modifier.padding(start = 16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
                Text(
                    text = book.author,
                    modifier = Modifier.padding(start = 18.dp, top = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchResultPreview(){
    SearchResult(
        book = Data.bookTitles.first(),
        onCardClick = {}
    )
}