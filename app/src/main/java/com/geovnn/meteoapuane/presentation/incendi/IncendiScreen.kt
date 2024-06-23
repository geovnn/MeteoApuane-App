package com.geovnn.meteoapuane.presentation.incendi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.home.HomeState
import com.geovnn.meteoapuane.presentation.home.composables.AllertaCard
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IncendiScreen(
    uiState: IncendiState,
    refreshData: () -> Unit,
) {
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        isRefreshing = true
        refreshData()
        delay(1000)
        isRefreshing = false
    }
    val state = rememberPullRefreshState(isRefreshing, ::refresh)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
            )
        } else if (uiState.error != "") {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    TextButton(onClick = { refreshData() }) {
                        Text(text = "Riprova")
                    }
                },
                title = { Text(text = "Errore") },
                text = { Text(text = uiState.error) },
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        } else {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Column {
                        TitleText(
                            modifier = Modifier.fillMaxWidth(),
                            text = "RISCHIO INCENDI BOSCHIVI")
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Settore COSTA",
                                    textAlign = TextAlign.Center,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "comuni di Massa, Carrara e Montignoso",
                                    textAlign = TextAlign.Center,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
                                )
                            }
                            VerticalDivider(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Settore LUNIGIANA",
                                    textAlign = TextAlign.Center,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "restanti comuni della provincia",
                                    textAlign = TextAlign.Center,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
                                )
                            }

//                            BodyText(modifier = Modifier.weight(1f),text = "Settore COSTA comuni di Massa, Carrara e Montignoso")
//                            BodyText(modifier = Modifier.weight(1f),text = "Settore LUNIGIANA restanti comuni della provincia")
                        }
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        TitleText(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(5.dp),
                            text = uiState.incendiPage.dataOggi)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.costaOggi?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }

                            }
                            VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer)

                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.lunigianaOggi?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }
                            }
                        }
                        TitleText(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(5.dp),
                            text = uiState.incendiPage.dataDomani)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.costaDomani?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }

                            }
                            VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer)

                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.lunigianaDomani?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }
                            }
                        }
                        TitleText(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(5.dp),
                            text = uiState.incendiPage.dataDopodomani)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.costaDopodomani?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }

                            }
                            VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer)

                            Box(modifier = Modifier.weight(1f)) {
                                uiState.incendiPage.lunigianaDopodomani?.let { Image(
                                    modifier = Modifier.fillMaxSize(),
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                ) }
                            }
                        }
                    }
                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Column {
                        TitleText(modifier = Modifier.fillMaxWidth(),text = "LEGENDA")

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFF92D050))
                                .padding(2.dp),
                            text = "BASSO",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        )
                        Text(
                            text = "Le probabilità che si inneschi un incendio sono basse, sono le condizioni migliori per svolgere attività lavorative che prevedano l’utilizzo di fiamma libera al di fuori del periodo a rischio."
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFFFFFF00))
                                .padding(2.dp),
                            text = "MODERATO",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        )
                        Text(
                            text = "Con queste condizioni l’accensione di fiamme libere è ancora possibile al di fuori del periodo a rischio ma adottando le dovute attenzioni, in quanto le condizioni ambientali sono al limite."
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFFFF6600))
                                .padding(2.dp),
                            text = "ALTO",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        )
                        Text(
                            text = "Con queste condizioni di rischio le probabilità che si inneschi un incendio pericoloso e difficilmente controllabile sono alte. Divieto di accensione."
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFFFF0000))
                                .padding(2.dp),
                            text = "MOLTO ALTO",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        )
                        Text(
                            text = "Con queste condizioni di rischio qualsiasi attività con fiamma libera è assolutamente vietata."
                        )
                    }
                }
            }
        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))

    }
}