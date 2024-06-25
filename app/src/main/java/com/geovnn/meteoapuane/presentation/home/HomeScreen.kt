package com.geovnn.meteoapuane.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.home.composables.AllertaCard
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import com.geovnn.meteoapuane.presentation.home.composables.UltimoraCard
import com.geovnn.meteoapuane.presentation.utils.composables.UltimoAggiornamentoText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeState,
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
    LaunchedEffect(Unit) {
        refreshData()
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
        } else if (uiState.error!="") {
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
            val allertaList= listOf(
                uiState.homePage.imgAllerta1,
                uiState.homePage.imgAllerta2,
                uiState.homePage.imgAllerta3,
                uiState.homePage.imgAllerta4,
                uiState.homePage.imgAllerta5,
                uiState.homePage.imgAllerta6
            )
            val cardPadding=7.dp
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                UltimoAggiornamentoText(text = uiState.homePage.txtUltimaModifia)

//                Text(
//                    text = uiState.homePage.txtUltimaModifia,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(3.dp),
//                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                    textAlign = TextAlign.Center,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
                ElevatedCard(
                    modifier = Modifier.padding(cardPadding),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "ALLERTA METEO")
                    AllertaCard(list = allertaList)
                }

                ElevatedCard(
                    modifier = Modifier.padding(cardPadding),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "BACHECA AVVISI",
                    )
                    BodyText(
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        text = uiState.homePage.txtAvviso
                    )
                }

                ElevatedCard(
                    modifier = Modifier.padding(cardPadding),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "ULTIM'ORA DALLA PROVINCIA")
                    UltimoraCard(
                        image = uiState.homePage.imgUltimora1,
                        title = uiState.homePage.txtUltimoraTitle1,
                        body = uiState.homePage.txtUltimoraBody1,
                    )
                    UltimoraCard(
                        image = uiState.homePage.imgUltimora2,
                        title = uiState.homePage.txtUltimoraTitle2,
                        body = uiState.homePage.txtUltimoraBody2,
                    )
                }
            }
        }
                    PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))
    }
}




