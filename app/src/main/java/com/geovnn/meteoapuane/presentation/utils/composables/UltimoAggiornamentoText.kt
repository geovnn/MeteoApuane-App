package com.geovnn.meteoapuane.presentation.utils.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            min = 8.sp,
            max = 15.sp,
        ),
        color = color,
        maxLines = 2,
        textAlign = TextAlign.Center
    )
}