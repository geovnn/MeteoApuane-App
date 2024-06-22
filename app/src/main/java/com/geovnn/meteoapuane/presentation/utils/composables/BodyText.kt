package com.geovnn.meteoapuane.presentation.utils.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        color = MaterialTheme.colorScheme.onSecondaryContainer,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
    )
}
