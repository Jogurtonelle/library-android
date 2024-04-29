package com.jogurtonelle.library.model

data class Notification(
    val type: NotificationType,
    val books: List<BookTitle>,
    //val dateOfRecieving: String,
    val branchID: Int,
    val dateOfReturn: String? = null
)

enum class NotificationType {
    ORDER_IN_PROGRESS,
    ORDER_READY,
    RETURN_DATE_REMINDER,
}