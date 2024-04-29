package com.jogurtonelle.library.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val email = remember{mutableStateOf("")}
    val password = remember{ mutableStateOf("") }
    Column (
        modifier = modifier
    ){
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { "Email" },
            maxLines = 1,
            modifier = Modifier
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { "Password" },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
        )
        Button(onClick = { onLoginClick(email.value, password.value) }) {
            Text(text = "Login")
        }
    }

}