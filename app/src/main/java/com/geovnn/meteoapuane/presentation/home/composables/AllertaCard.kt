package com.geovnn.meteoapuane.presentation.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun AllertaCard(
    list: List<String>,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White,
        ),
    ) {
        Row {
            repeat(list.size) { index ->
                if (list[index]!="") {
                    ImageCoil(
                        url = list[index],
                        contentDescription = "",
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(2.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}