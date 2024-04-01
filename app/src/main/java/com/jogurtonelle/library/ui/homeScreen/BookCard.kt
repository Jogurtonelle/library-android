package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jogurtonelle.library.R
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.theme.LibraryTheme

@Composable
fun BookCard(
    book: BookTitle,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .widthIn(max = 146.dp, min = 146.dp)
            .heightIn(min = 292.dp, max = 292.dp)
            .clickable(
                enabled = true,
                onClick = onCardClicked,
                onClickLabel = "Choose: ${book.title}"
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model = book.coverURL,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier
                        .padding(bottom = 16.dp)
                        .width(160.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
            )

            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = book.author,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 120, heightDp = 320)
@Composable
fun BookCardPreview() {
    LibraryTheme{
        //BookCard("Rodzina Monet - Skarb", author = "W. A. Marczak", cover = R.drawable.monet)
    }
}