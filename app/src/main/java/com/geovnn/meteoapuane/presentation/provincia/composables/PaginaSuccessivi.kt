package com.geovnn.meteoapuane.presentation.provincia.composables

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.domain.models.ProvinciaPageSuccessivi
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText

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
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            BodyText(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                TitleText(modifier = Modifier.fillMaxWidth(), text = uiState.data1)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgA1,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgB1,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgC1,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    BodyText(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita1,
                    )
                }
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                TitleText(modifier = Modifier.fillMaxWidth(), text = uiState.data2)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgA2,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgB2,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    ImageCoil(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        url = uiState.imgC2,
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyText(
                        modifier = Modifier
                            .weight(5f)
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    BodyText(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita2,
                    )
                }
            }
        }
    }
}