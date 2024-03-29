package com.jogurtonelle.library.ui.homeScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.ui.LibraryApp

@Composable
fun LibraryTextField(
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        //label = { Text("Szukaj książki") },
        placeholder = { Text("Wpisz tytuł, autora lub numer ISBN") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun LibraryAppPreview() {
    LibraryApp()
}