package com.jogurtonelle.library.ui.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.model.NotificationType


@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier
){
    val data = Data.notifications

    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(data){ notification ->
            when (notification.type){
                NotificationType.ORDER_READY -> OrderReadyNotification(
                    notification = notification
                )
                NotificationType.RETURN_DATE_REMINDER -> ReturnDateNotification(
                    notification = notification
                )
                NotificationType.ORDER_IN_PROGRESS -> OrderInProgressNotification(
                    notification = notification
                )
            }
        }
    }
}