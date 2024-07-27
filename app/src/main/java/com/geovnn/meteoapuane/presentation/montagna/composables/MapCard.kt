package com.geovnn.meteoapuane.presentation.montagna.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCard(
    modifier: Modifier = Modifier,
    multiplierDp: Dp,
    multiplierSp: TextUnit,
    offsetX: Dp,
    offsetY: Dp,
    titolo: String,
    altitudine: String,
    data1: String,
    icona1: String,
    data2: String,
    icona2: String,
    data3: String,
    icona3: String,
    zeroTermico: String,
    altezzaNeve: String,
) {
    var parentSize by remember { mutableStateOf<IntSize?>(null) }
    val cardSize = multiplierDp*40
    val iconSize = multiplierDp*10
    val fontSizeTitolo = multiplierSp*2.8
    val fontSizeData = multiplierSp*2.5
    val fontSizeZeroAltezza = multiplierSp*2
    Box(
        modifier = Modifier
            .size(cardSize)
            .aspectRatio(1.91F)
            .absoluteOffset(offsetX - cardSize / 2, offsetY - cardSize / 2)
            .onGloballyPositioned {
                //here u can access the parent layout coordinate size
                parentSize = it.parentLayoutCoordinates?.size
            }
    ) {
//        val multiplierDp = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 100).toDp() }
        Card(
            modifier = modifier
                .padding(1.dp)
//                .border(0.5.dp,MaterialTheme.colorScheme.outline,RoundedCornerShape(5))
                .fillMaxSize(),
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
            ) {
                AutoResizeText(
                    modifier= Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                        .fillMaxSize(),
                    text = "$titolo - $altitudine",
                    fontSizeRange = FontSizeRange(
                        min = MaterialTheme.typography.labelSmall.fontSize,
                        max = MaterialTheme.typography.titleLarge.fontSize,
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
                Row(
                    modifier= Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier= Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AutoResizeText(
                            modifier= Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            text = data1,
                            fontSizeRange = FontSizeRange(
                                min = MaterialTheme.typography.labelSmall.fontSize,
                                max = MaterialTheme.typography.titleLarge.fontSize,
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        ImageCoil(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            url = icona1, contentDescription = "")
                    }
                    Column(
                        modifier= Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AutoResizeText(
                            modifier= Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            text = data2,
                            fontSizeRange = FontSizeRange(
                                min = MaterialTheme.typography.labelSmall.fontSize,
                                max = MaterialTheme.typography.titleLarge.fontSize,
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        ImageCoil(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            url = icona2, contentDescription = "")
                    }
                    Column(
                        modifier= Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AutoResizeText(
                            modifier= Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            text = data3,
                            fontSizeRange = FontSizeRange(
                                min = MaterialTheme.typography.labelSmall.fontSize,
                                max = MaterialTheme.typography.titleLarge.fontSize,
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        ImageCoil(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            url = icona3, contentDescription = "")
                    }
                }
//                Text(
//                    modifier= Modifier
//                        .weight(1f)
//                        .fillMaxSize(),
//                    text = "Zero Termico $zeroTermico Altezza Neve $altezzaNeve",
//                    fontSize = fontSizeZeroAltezza,
//                    textAlign = TextAlign.Center
//                )
                AutoResizeText(
//                    text = "Zero Termico $zeroTermico Altezza Neve $altezzaNeve",
                    text = "Zero Termico: $zeroTermico | Altezza Neve: $altezzaNeve",
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                        .fillMaxSize(),
                    fontSizeRange = FontSizeRange(
                        min = 1.sp,
                        max = 100.sp,
                    ),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

        }
    }


}