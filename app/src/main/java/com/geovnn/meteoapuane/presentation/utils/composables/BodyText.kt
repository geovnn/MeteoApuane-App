package com.geovnn.meteoapuane.presentation.utils.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
    )
}
