package com.jogurtonelle.library.model

import androidx.annotation.DrawableRes

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    @DrawableRes val coverId: Int
)