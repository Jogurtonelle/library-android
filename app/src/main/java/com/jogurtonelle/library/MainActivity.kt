package com.jogurtonelle.library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jogurtonelle.library.theme.LibraryTheme
import com.jogurtonelle.library.ui.LibraryApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {
                LibraryApp()
            }
        }
    }
}