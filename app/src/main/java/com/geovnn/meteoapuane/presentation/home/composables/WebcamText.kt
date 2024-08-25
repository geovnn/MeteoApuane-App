package com.geovnn.meteoapuane.presentation.home.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WebcamText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
        fontStyle = MaterialTheme.typography.bodySmall.fontStyle
    )
}
