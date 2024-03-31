package com.jogurtonelle.library.ui.qrCodeSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jogurtonelle.library.data.Data
import com.jogurtonelle.library.theme.Oswald
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCodeBottomSheet(
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
    ){
        Box{
            Column{
                Text(
                    text = "Twoja karta biblioteczna",
                    modifier = Modifier.padding(start = 24.dp, end = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = Oswald,
                    fontSize = 32.sp
                )
                QrCodeView(
                    data = Data.user.id.toString(),
                    modifier = Modifier
                        .padding(start = 72.dp, end = 72.dp, top = 16.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colors = QrCodeColors(
                        background = BottomSheetDefaults.ContainerColor,
                        foreground = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = Data.user.id.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                )
            }
        }
    }
}