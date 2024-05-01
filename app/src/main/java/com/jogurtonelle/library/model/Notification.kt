package com.jogurtonelle.library.model

data class Notification(
    val id: String = "1",
    val type: NotificationType = NotificationType.ORDER_IN_PROGRESS,
    val book: BookTitle = BookTitle(),
    val branchID: Int = 0,
    val dateOfReturn: String? = null
)

enum class NotificationType {
    ORDER_IN_PROGRESS,
    ORDER_READY,
    RETURN_DATE_REMINDER,
}