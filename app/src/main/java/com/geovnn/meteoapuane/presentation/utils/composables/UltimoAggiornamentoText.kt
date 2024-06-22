package com.geovnn.meteoapuane.presentation.utils.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange

@Composable
fun UltimoAggiornamentoText(
    modifier: Modifier = Modifier,
    text : String? = null,
    color : Color = MaterialTheme.colorScheme.onBackground
) {
    AutoResizeText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        text = text ?: "",
        fontSizeRange = FontSizeRange(
            min = MaterialTheme.typography.labelSmall.fontSize,
            max = MaterialTheme.typography.titleLarge.fontSize,
        ),
        color = color,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}