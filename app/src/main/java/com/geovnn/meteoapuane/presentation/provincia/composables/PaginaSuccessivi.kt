package com.geovnn.meteoapuane.presentation.provincia.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.domain.models.ProvinciaPageSuccessivi

@Composable
fun PaginaSuccessivi(
    modifier : Modifier = Modifier,
    uiState: ProvinciaPageSuccessivi
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = uiState.data1, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    uiState.imgA1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    uiState.imgB1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    uiState.imgC1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita1,
                    )
                }
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = uiState.data2, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    uiState.imgA2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    uiState.imgB2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    uiState.imgC2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita2,
                    )
                }
            }
        }
    }
}

