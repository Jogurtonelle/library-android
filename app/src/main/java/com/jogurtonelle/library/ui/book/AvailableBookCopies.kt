package com.jogurtonelle.library.ui.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.model.BookCopy
import com.jogurtonelle.library.model.BookStatus

@Composable
fun AvailableBookCopies(
    availableCopies: List<BookCopy>,
    branch: Int
) {
    val branchName = if (branch == 0)  "Biblioteka główna" else "Filia nr $branch"
    val availableCopiesInBranch = availableCopies.filter { it.libraryBranchId == branch }
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

    for (copy in availableCopiesInBranch) {
        val isAvailable = (copy.status == BookStatus.AVAILABLE)

        Card(
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
                            color = if (isAvailable) MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                        )
                        Text(
                            text = copy.yearOfPublication.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(
                                    end = 16.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                ),
                            color = if (isAvailable) MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
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
                            color = if (isAvailable) MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                        )
                        Text(
                            text = if (isAvailable) "Dostępna" else "po " + copy.dateOfReturn,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(
                                    end = 16.dp,
                                    top = 4.dp,
                                    bottom = 8.dp
                                ),
                            color = if (isAvailable) MaterialTheme.colorScheme.onTertiaryContainer else Color.Gray
                        )
                    }
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 12.dp),
                    shape = CardDefaults.shape,
                    enabled = isAvailable,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAvailable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    if (isAvailable) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Zarezerwuj")
                    }
                    else{
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Niedostępna")
                    }
                }
            }
        }
    }
}