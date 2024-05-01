package com.jogurtonelle.library.ui.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jogurtonelle.library.R
import com.jogurtonelle.library.model.Notification

@Composable
fun OrderInProgressNotification(
    notification: Notification,
    modifier: Modifier = Modifier
){
    var showDetails by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    showDetails = !showDetails
                }
            )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(

            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Twoje zamówienie w filli nr ${notification.branchID} jest przygotowywane",
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Średni czas oczekiwania to 30-45 minut"
                    )
                }

                Icon(
                    imageVector = if (showDetails) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                )

            }

            if (showDetails){
                Column {
                    Text(
                        text = "Zamówiona książka:",
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    val book = notification.book

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                    ){
                        AsyncImage(
                            model = book.coverURL,
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.baseline_image_24),
                            modifier = Modifier
                                .width(56.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                        )
                        Column (
                            modifier = Modifier.padding(start = 8.dp)
                        ){
                            Text(
                                text = book.title,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = book.author,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }



                }
            }
        }
    }
}
//
//@Preview
//@Composable
//fun OrderInProgressNotificationPreview(){
//    OrderInProgressNotification(
//        notification = Notification(
//            branchID = 1,
//            type = NotificationType.ORDER_IN_PROGRESS,
//            books = listOf(Data.bookTitles[0], Data.bookTitles[1])
//        )
//    )
//}