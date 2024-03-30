package com.jogurtonelle.library.ui.barcodeScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jogurtonelle.library.R

@Composable
fun FloatingActionButtonCard(
    onYourCardClick: () -> Unit
) {
    Button(
        onClick = onYourCardClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(R.drawable.baseline_qr_code_24),
                contentDescription = "Okaż kartę biblioteczną"
            )
            Text(
                text = "Twoja karta",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}