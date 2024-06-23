package com.geovnn.meteoapuane.presentation.viabilita.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SegnalazioneItemText(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        maxLines = 1,
        fontSize = 9.sp,
        fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
    )
}