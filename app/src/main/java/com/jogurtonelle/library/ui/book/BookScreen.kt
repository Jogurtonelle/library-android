package com.jogurtonelle.library.ui.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.model.BookStatus
import com.jogurtonelle.library.theme.Oswald

@Composable
fun BookScreen(
    bookId : Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    val book = Data.books.first { it.id == bookId }
    val availableCopies = Data.bookItems.filter { it.bookId == book.id }.sortedBy { it.libraryBranchId }
    val libraryBranches = availableCopies.map { it.libraryBranchId }.distinct().sorted()

    Scaffold (
        containerColor = MaterialTheme.colorScheme.surface
    ){
        paddingValues ->
        LazyColumn (
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = paddingValues,
        ){
            item{
                Image(
                    painter = painterResource(id = book.coverId),
                    contentDescription = book.title,
                    modifier = modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
            }

            item {
                TitleDescription(book = book)
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
                val branchName = if (branch == 0)  "Biblioteka główna" else "Filia nr $branch"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = branchName,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                for (item in availableCopies.filter { it.libraryBranchId == branch }) {
                    val available = if (item.status == BookStatus.AVAILABLE) "Dostępna" else "po " + item.dateOfReturn
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column(
                                modifier = Modifier.weight(1f),
                            ){
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        text = "Rok publikacji:  ",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 4.dp,
                                                bottom = 4.dp
                                            ),
                                        color = if (available == "Dostępna") MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                                    )
                                    Text(
                                        text = item.yearOfPublication.toString(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(
                                                end = 16.dp,
                                                top = 4.dp,
                                                bottom = 4.dp
                                            ),
                                        color = if (available == "Dostępna") MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                                    )
                                }
                                Row (
                                    verticalAlignment = Alignment.Bottom
                                ){
                                    Text(
                                        text = "Dostępność:  ",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 4.dp,
                                                bottom = 8.dp
                                            ),
                                        color = if (available == "Dostępna") MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                                    )
                                    Text(
                                        text = available,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(
                                                end = 16.dp,
                                                top = 4.dp,
                                                bottom = 8.dp
                                            ),
                                        color = if (available == "Dostępna") MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                                    )
                                }
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 12.dp),
                                shape = CardDefaults.shape,
                                enabled = (available == "Dostępna"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (available == "Dostępna") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                if (available == "Dostępna") {
                                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Zarezerwuj")
                                }
                                else{
                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Zarezerwuj")
                                }
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}