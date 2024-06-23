package com.geovnn.meteoapuane.presentation.home.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText

@Composable
fun WebcamText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        color = MaterialTheme.colorScheme.onSecondaryContainer,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
        fontStyle = MaterialTheme.typography.bodySmall.fontStyle
    )
}
