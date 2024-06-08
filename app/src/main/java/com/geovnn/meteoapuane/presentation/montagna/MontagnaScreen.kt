package com.geovnn.meteoapuane.presentation.montagna

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.montagna.composables.MappaMontagna
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MontagnaScreen(
    uiState: MontagnaUiState,
    onMenuClick: () -> Unit,
    refreshData: () -> Unit
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = "Montagna",
                    color = MaterialTheme.colorScheme.onPrimaryContainer) },
                navigationIcon = {
                    IconButton(onClick = { onMenuClick() }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },

        ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pullRefresh(state)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else if (uiState.error!="") {
                AlertDialog(
                    onDismissRequest = {  },
                    confirmButton = { TextButton(onClick = { refreshData() }) {
                        Text(text = "Riprova")
                    } },
                    title = { Text(text = "Errore") },
                    text = { Text(text = uiState.error) }
                )
            } else {
                Column(
                    modifier=Modifier.verticalScroll(rememberScrollState())
                ) {
//                    Text(
//                        text = uiState.testoUltimoAggiornamento,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(3.dp),
//                        fontSize = 14.sp,
//
//                        textAlign = TextAlign.Center,
//                    )

                    AutoResizeText(
                        text = uiState.montagnaPage.testoUltimoAggiornamento,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                        fontSizeRange = FontSizeRange(
                            min = 10.sp,
                            max = 22.sp,
                        ),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                    )

//                    Divider()

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "BOLLETTINO MONTAGNA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Previsioni a 3 giorni per le principali località sciistiche e montane delle Apuane ed Appennino settentrionale ",
                        fontWeight = FontWeight.Bold,
//                                fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    MappaMontagna(uiState = uiState)
                    ElevatedCard(
                        modifier = Modifier.padding(5.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(5.dp)) {


                            Text(
                                text = uiState.montagnaPage.testo,
                                modifier = Modifier
                                    .fillMaxWidth()
//                                    .padding(3.dp),
//                        fontSize = 14.sp,
                            )
                        }
                    }


                }
            }
        }
    }
}

